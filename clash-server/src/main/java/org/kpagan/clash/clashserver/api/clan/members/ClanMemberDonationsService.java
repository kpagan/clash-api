package org.kpagan.clash.clashserver.api.clan.members;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.kpagan.clash.clashserver.api.BaseService;
import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
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
	private StatisticsCalculationService statService;
	
	@Transactional
	public void getClanMemberDonations(String clanTag) {
		try {
			List<Future<PlayerDetailsInfo>> futures = clanMemberListService.getClanMembersAsync(clanTag);
			
			int totalMembers = futures.size();
			int current = 0;
			List<String> memberTags = Collections.synchronizedList(new ArrayList<>(totalMembers));
			List<ClanMember> members = Collections.synchronizedList(new ArrayList<>());
			
			for (Future<PlayerDetailsInfo> future : futures) {
				double progress = ((double) ++current / totalMembers) * 100;
				PlayerDetailsInfo player = future.get();
				log.info("Got response for member {}. Progress: {}%", player.getName(),
						BaseService.percentageFormatter.format(progress));
				Optional<ClanMember> dbPlayer = repo.findById(player.getTag());
				members.add(statService.calculate(player, dbPlayer));
				memberTags.add(player.getTag());
				log.info("Finished member {}", player.getName());
			}
			
			List<ClanMember> clanMembersLeft = repo.findByTagNotIn(memberTags);
			statService.calculateLeftMembersStats(clanMembersLeft);
			members.addAll(clanMembersLeft);

			repo.saveAll(members);
		} catch (Exception e) {
			log.error("Error while updating statistics for clan {}", clanTag, e);
		}
	}

}
