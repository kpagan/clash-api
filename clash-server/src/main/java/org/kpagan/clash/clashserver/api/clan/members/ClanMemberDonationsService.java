package org.kpagan.clash.clashserver.api.clan.members;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

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

	@Transactional
	public void getClanMemberDetails(String clanTag) {
		List<Future<PlayerDetailsInfo>> futures = clanMemberListService.getClanMembersAsync(clanTag);

		int totalMembers = futures.size();
		int current = 0;
		List<String> memberTags = new ArrayList<>(totalMembers);
		List<ClanMember> members = new ArrayList<>();
		for (Future<PlayerDetailsInfo> future : futures) {
			double progress = ((double) ++current / totalMembers) * 100;
			try {
				PlayerDetailsInfo player = future.get();
				log.info("Got response for member {}. Progress: {}%", player.getName(), BaseService.percentageFormatter.format(progress));
				Optional<ClanMember> dbPlayer = repo.findById(player.getTag());
				members.add(map(player, dbPlayer));
				memberTags.add(player.getTag());
			} catch (Exception e) {
				log.error("Error while getting details info for player", e);
			}
		}
		
		List<ClanMember> clanMembersLeft = repo.findClanMembersNotIn(memberTags);
		for (ClanMember leftMember: clanMembersLeft) {
			leftMember.setLeftClan(LocalDate.now(ClashConfig.ATHENS));
			calculateDonations(leftMember);
			members.add(leftMember);
		}
		
		repo.saveAll(members);
	}
	
	private ClanMember map(PlayerDetailsInfo player, Optional<ClanMember> dbPlayer) {
		if (dbPlayer.isPresent()) {
			ClanMember clanMember = dbPlayer.get();
			if (clanMember.getLeftClan() != null) {
				// player left before and rejoined, reset donations
				populate(player, clanMember);
			} else {
				if (player.getDonations() < clanMember.getLastDayDonations()) {
					// update player donations from join day etc only after the change of the week
					calculateDonations(clanMember);
				} else {
					// update last day donations only
					clanMember.setLastDayDonations(player.getDonations());
					clanMember.setLastDayDonationsReceived(player.getDonationsReceived());
				}
			}
			return clanMember;
		} else {
			// player joins for the 1st time
			ClanMember member = new ClanMember();
			member.setTag(player.getTag());
			member.setName(player.getName());
			populate(player, member);
			return member;
		}
	}

	private void calculateDonations(ClanMember clanMember) {
		clanMember.setDonatedFromJoinDay(clanMember.getDonatedFromJoinDay() + clanMember.getLastDayDonations());
		clanMember.setReceivedFromJoinDay(clanMember.getReceivedFromJoinDay() + clanMember.getLastDayDonationsReceived());
		// update average donations by summing previous average with current donations and divide by two
		clanMember.setAverageWeeklyDonations((int) Math.round(((double) (clanMember.getAverageWeeklyDonations() + clanMember.getLastDayDonations())) / 2));
	}

	private void populate(PlayerDetailsInfo player, ClanMember member) {
		member.setMemberSince(LocalDate.now(ClashConfig.ATHENS));
		member.setDonatedFromJoinDay(player.getDonations());
		member.setAverageWeeklyDonations(player.getDonations());
		member.setLastDayDonations(player.getDonations());
		member.setReceivedFromJoinDay(player.getDonationsReceived());
		member.setLastDayDonationsReceived(player.getDonationsReceived());
		member.setLeftClan(null);
	}
}
