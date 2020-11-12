package org.kpagan.clash.clashserver.api.clan.members;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
import org.kpagan.clash.clashserver.config.ClashConfig;
import org.kpagan.clash.clashserver.domain.ClanMember;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class StatisticsCalculationService {
	
	private Clock clock = Clock.system(ClashConfig.ATHENS);

	public synchronized ClanMember calculate(PlayerDetailsInfo player, Optional<ClanMember> dbPlayer) {
		if (dbPlayer.isPresent()) {
			ClanMember clanMember = dbPlayer.get();
			clanMember.setName(player.getName()); // update name in case it is changed
			if (clanMember.getLeftClan() != null) {
				log.info("Player {} rejoined, he left on {}", clanMember.getName(), clanMember.getLeftClan());
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

	public synchronized void calculateLeftMembersStats(List<ClanMember> clanMembersLeft) {
		log.info("Calculating left members statistics");
		for (ClanMember leftMember : clanMembersLeft) {
			// compute donations only for members that left recently, do not recalculate
			// members left previously
			log.info("Player {} left clan on: {}", leftMember.getName(), leftMember.getLeftClan());
			if (leftMember.getLeftClan() == null) {
				leftMember.setLeftClan(LocalDate.now(clock));
				calculateDonations(leftMember);
			}
		}
	}
	
	// returns true when time is between 00:00 and 00:01 on Mondays
	boolean isWeekReset() {
		LocalTime time = LocalTime.now(clock);
		LocalDate date = LocalDate.now(clock);
		log.info("The time right now is {} {}", date, time);
		return date.getDayOfWeek() == DayOfWeek.MONDAY
				&& time.isAfter(LocalTime.MIDNIGHT)
				&& time.isBefore(LocalTime.MIDNIGHT.plusMinutes(1));
	}
	
	private void calculateDonations(ClanMember clanMember) {
		log.info("Player {} has donated from join day: {}, current donations are: {}", clanMember.getName(), clanMember.getDonatedFromJoinDay(), clanMember.getWeekDonationsSoFar());
		clanMember.setDonatedFromJoinDay(clanMember.getDonatedFromJoinDay() + clanMember.getWeekDonationsSoFar());
		log.info("Player {} has received donations from join day: {}, current received donations are: {}",
				clanMember.getName(), clanMember.getReceivedFromJoinDay(), clanMember.getWeekDonationsReceivedSoFar());
		clanMember.setReceivedFromJoinDay(
				clanMember.getReceivedFromJoinDay() + clanMember.getWeekDonationsReceivedSoFar());
		int avg = clanMember.getAverageWeeklyDonations();
		int avgCount = clanMember.getAverageWeeklyDonationsCount();
		log.info("Player {} has average weekly donations: {}", clanMember.getName(), avg);
		if (avg == 0) {
			// for the 1st time just get the donations so far
			avg = clanMember.getWeekDonationsSoFar();
		} else {
			// update average donations by summing (previous average)*(previous count) + current donations
			// and divide by the count
			avg = (int) Math.round(
					((double) (avgCount * clanMember.getAverageWeeklyDonations() + clanMember.getWeekDonationsSoFar())) / (avgCount + 1));
		}
		log.info("Player {} NEW average weekly donations: {}", clanMember.getName(), avg);
		clanMember.setAverageWeeklyDonations(avg);
		clanMember.setAverageWeeklyDonationsCount(avgCount + 1);
	}

	private void initPlayer(PlayerDetailsInfo player, ClanMember member) {
		member.setMemberSince(LocalDate.now(clock));
		// initialize donations to zero since the statistics are calculated when the
		// week changes
		member.setDonatedFromJoinDay(0);
		member.setAverageWeeklyDonations(0);
		member.setAverageWeeklyDonationsCount(1);
		member.setReceivedFromJoinDay(0);
		member.setWeekDonationsSoFar(player.getDonations());
		member.setWeekDonationsReceivedSoFar(player.getDonationsReceived());
		member.setLeftClan(null);
	}

	/**
	 * Overriding system clock by unit tests
	 * @param clock usually a fixed Clock see {@link Clock#fixed}
	 */
	void setClock(Clock clock) {
		this.clock = clock;
	}

}


