package org.kpagan.clash.clashserver.api.clan.members;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.kpagan.clash.clashserver.api.player.battlelog.BattleLogInfo;
import org.kpagan.clash.clashserver.api.player.battlelog.BattleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class IdleClanMemberService {

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'.'SSS'Z'");
	private static final DecimalFormat percentageFormatter = new DecimalFormat("#");

	@Autowired
	private ClanMemberListService clanMemberListService;

	@Autowired
	private BattleLogService battleLogService;

	/**
	 * Returns a list of idle players of a clan.
	 * Idle player is considered a player with 0 donations that have not played a battle for a long time (e.g. more than 2 months)
	 * @param clanTag
	 * @return
	 */
	public List<ClanMemberInfo> getIdlePlayers(String clanTag) {
		List<ClanMemberInfo> idlePlayers = new ArrayList<>();
		ClanMemberListInfo clanMembers = clanMemberListService.getClanMembers(clanTag);
		int totalMembers = clanMembers.getItems().size();
		int current = 0;
		for (ClanMemberInfo member : clanMembers.getItems()) {
			double progress = ((double)++current/totalMembers)*100;
			log.info("Looking for member {}. Progress: {}%", member.getName(), percentageFormatter.format(progress)); 
			if (member.getDonations() == 0) {
				List<BattleLogInfo> battleLog = battleLogService.getBattleLog(member.getTag());
				if (!CollectionUtils.isEmpty(battleLog)) {
					// the 1st battleLog entry will be the most recent
					BattleLogInfo battleLogInfo = battleLog.get(0);
					LocalDate battleDate = LocalDate.parse(battleLogInfo.getBattleTime(), dateFormatter);
					// if no battle has been done for the past 2 months then consider the player as idle
					if (battleDate.isBefore(LocalDate.now().minusMonths(2L))) {
						idlePlayers.add(member);
					}
				} else {
					idlePlayers.add(member); // idle players for so long will have empty battle log
				}
			}
		}
		return idlePlayers;
	}
}
