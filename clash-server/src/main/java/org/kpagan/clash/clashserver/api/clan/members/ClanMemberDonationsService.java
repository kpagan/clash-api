package org.kpagan.clash.clashserver.api.clan.members;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import org.kpagan.clash.clashserver.api.BaseService;
import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
import org.kpagan.clash.clashserver.config.ClashConfig;
import org.kpagan.clash.clashserver.domain.ClanMember;
import org.kpagan.clash.clashserver.domain.ClanMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClanMemberDonationsService {

	@Autowired
	private ClanMemberListService clanMemberListService;

	@Autowired
	private ClanMemberRepository repo;

	@Autowired
	private ExecutorService executor;

	private final ReentrantLock lock = new ReentrantLock();
	
	@Transactional
	public void getClanMemberDonations(String clanTag) {
		List<Future<PlayerDetailsInfo>> futures = clanMemberListService.getClanMembersAsync(clanTag);

		int totalMembers = futures.size();
		int current = 0;
		List<String> memberTags = Collections.synchronizedList(new ArrayList<>(totalMembers));
		List<ClanMember> members = Collections.synchronizedList(new ArrayList<>());
		CountDownLatch currentMembersLatch = new CountDownLatch(totalMembers);

		for (Future<PlayerDetailsInfo> future : futures) {
			double progress = ((double) ++current / totalMembers) * 100;
			executor.execute(() -> {
				try {
					PlayerDetailsInfo player = future.get();
					log.info("Got response for member {}. Progress: {}%", player.getName(),
							BaseService.percentageFormatter.format(progress));
					Optional<ClanMember> dbPlayer = repo.findById(player.getTag());
					members.add(map(player, dbPlayer));
					memberTags.add(player.getTag());
				} catch (Exception e) {
					log.error("Error while getting details info for player", e);
				} finally {
					currentMembersLatch.countDown();
					log.info("Finished member {}", currentMembersLatch.getCount());
				}
			});
		}
		
		try {
			currentMembersLatch.await();
		} catch (InterruptedException e) {
			log.error("Error waiting threads to complete tasks.", e);
		}

		List<ClanMember> clanMembersLeft = repo.findByTagNotIn(memberTags);
		CountDownLatch leftMembersLatch = new CountDownLatch(clanMembersLeft.size());
		for (ClanMember leftMember : clanMembersLeft) {
			// compute donations only for members that left recently, do not recalculate
			// members left previously
			lock.lock();
			try {
				log.info("Player {} left clan on: {}", leftMember.getName(), leftMember.getLeftClan());
				if (leftMember.getLeftClan() == null) {
					leftMember.setLeftClan(LocalDate.now(ClashConfig.ATHENS));
					calculateDonations(leftMember);
					members.add(leftMember);
				}
			} finally {
				lock.unlock();
				leftMembersLatch.countDown();
			}
		}
		
		try {
			leftMembersLatch.await();
		} catch (InterruptedException e) {
			log.error("Error waiting threads to complete tasks.", e);
		}

		repo.saveAll(members);
	}

	private synchronized ClanMember map(PlayerDetailsInfo player, Optional<ClanMember> dbPlayer) {
		if (dbPlayer.isPresent()) {
			ClanMember clanMember = dbPlayer.get();
			if (clanMember.getLeftClan() != null) {
				// player left before and rejoined, increase how many times has re-joined and
				// reset the date that left the clan
				clanMember.increaseTimesRejoined();
				clanMember.setLeftClan(null);
			}
			if (isWeekReset()) {
				// update player donations from join day etc only after the change of the week
				calculateDonations(clanMember);
			} else {
				// update last day donations only
				clanMember.setWeekDonationsSoFar(player.getDonations());
				clanMember.setWeekDonationsReceivedSoFar(player.getDonationsReceived());
			}
			return clanMember;
		} else {
			// player joins for the 1st time
			ClanMember member = new ClanMember();
			member.setTag(player.getTag());
			member.setName(player.getName());
			member.setClanTag(player.getClan().getTag());
			initPlayer(player, member);
			return member;
		}
	}

	// returns true when time is between 00:00 and 00:01 on Mondays
	private boolean isWeekReset() {
		return LocalDate.now(ClashConfig.ATHENS).getDayOfWeek() == DayOfWeek.MONDAY
				&& LocalTime.now(ClashConfig.ATHENS).isAfter(LocalTime.MIDNIGHT)
				&& LocalTime.now(ClashConfig.ATHENS).isBefore(LocalTime.MIDNIGHT.plusMinutes(1));
	}

	private void calculateDonations(ClanMember clanMember) {
		clanMember.setDonatedFromJoinDay(clanMember.getDonatedFromJoinDay() + clanMember.getWeekDonationsSoFar());
		clanMember.setReceivedFromJoinDay(
				clanMember.getReceivedFromJoinDay() + clanMember.getWeekDonationsReceivedSoFar());
		int avg = clanMember.getAverageWeeklyDonations();
		if (avg == 0) {
			// for the 1st time just get the donations so far
			avg = clanMember.getWeekDonationsSoFar();
		} else {
			// update average donations by summing previous average with current donations
			// and divide by two
			avg = (int) Math.round(
					((double) (clanMember.getAverageWeeklyDonations() + clanMember.getWeekDonationsSoFar())) / 2);
		}
		clanMember.setAverageWeeklyDonations(avg);
	}

	private void initPlayer(PlayerDetailsInfo player, ClanMember member) {
		member.setMemberSince(LocalDate.now(ClashConfig.ATHENS));
		// initialize donations to zero since the statistics are calculated when the
		// week changes
		member.setDonatedFromJoinDay(0);
		member.setAverageWeeklyDonations(0);
		member.setReceivedFromJoinDay(0);
		member.setWeekDonationsSoFar(player.getDonations());
		member.setWeekDonationsReceivedSoFar(player.getDonationsReceived());
		member.setLeftClan(null);
	}
}
