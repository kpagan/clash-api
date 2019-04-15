package org.kpagan.clash.clashserver.api.clan.members;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.kpagan.clash.clashserver.api.common.PlayerWrapperInfo;
import org.kpagan.clash.clashserver.api.player.battlelog.BattleLogInfo;
import org.kpagan.clash.clashserver.api.player.battlelog.BattleLogService;
import org.kpagan.clash.clashserver.api.player.battlelog.ClanMemberBattleLogInfo;
import org.springframework.beans.BeanUtils;
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

	@Autowired
	private ExecutorService executor;

	/**
	 * Returns a list of idle players of a clan. Idle player is considered a player
	 * with 0 donations that have not played a battle for a long time (e.g. more than 2 months)
	 * 
	 * @param clanTag
	 * @return
	 */
	public List<ClanMemberBattleLogInfo> getIdlePlayers(String clanTag) {
		List<ClanMemberBattleLogInfo> idlePlayers = new ArrayList<>();
		ClanMemberListInfo clanMembers = clanMemberListService.getClanMembers(clanTag);
		List<Future<PlayerWrapperInfo<BattleLogInfo>>> futures = new ArrayList<>();
		Map<String, ClanMemberInfo> members = new HashMap<>();
		for (ClanMemberInfo member : clanMembers.getItems()) {
			if (member.getDonations() == 0) {
				members.put(member.getTag(), member); // add the member here to lookup later
				Future<PlayerWrapperInfo<BattleLogInfo>> future = executor.submit(() -> {
					log.info("Looking for member {}", member.getName());
					List<BattleLogInfo> battleLog = battleLogService.getBattleLog(member.getTag());
					if (!CollectionUtils.isEmpty(battleLog)) {
						BattleLogInfo battleLogInfo = battleLog.get(0);
						return new PlayerWrapperInfo<BattleLogInfo>(member.getName(), member.getTag(), battleLogInfo);
					} else {
						return new PlayerWrapperInfo<BattleLogInfo>(member.getName(), member.getTag(), null);
					}
				});
				futures.add(future);
			}
		}
		int totalMembers = futures.size();
		int current = 0;

		for (Future<PlayerWrapperInfo<BattleLogInfo>> future : futures) {
			double progress = ((double) ++current / totalMembers) * 100;
			try {
				PlayerWrapperInfo<BattleLogInfo> playerInfo = future.get();
				log.info("Got response for member {}. Progress: {}%", playerInfo.getName(), percentageFormatter.format(progress));
				ClanMemberInfo member = members.get(playerInfo.getTag());
				LocalDateTime battleDate = null;
				if (playerInfo.getModel() != null) {
					// the 1st battleLog entry will be the most recent
					BattleLogInfo battleLogInfo = playerInfo.getModel();
					battleDate = LocalDateTime.parse(battleLogInfo.getBattleTime(), dateFormatter);
				}
				idlePlayers.add(map(member, battleDate)); // idle players for so long will have empty battle log
			} catch (Exception e) {
				log.error("Error while getting battle log info for player", e);
			}	
		}
		Collections.sort(idlePlayers);
		return idlePlayers;
	}
	
	private ClanMemberBattleLogInfo map(ClanMemberInfo member, LocalDateTime lastBattle) {
		ClanMemberBattleLogInfo info = new ClanMemberBattleLogInfo();
		BeanUtils.copyProperties(member, info);
		info.setLastBattle(lastBattle);
		return info;
		
	}
}
