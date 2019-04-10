package org.kpagan.clash.clashserver.api.player.battlelog;

import java.time.LocalDateTime;

import org.kpagan.clash.clashserver.api.common.ArenaInfo;

import lombok.Data;

@Data
public class ClanMemberBattleLogInfo implements Comparable<ClanMemberBattleLogInfo> {
	private String tag;
	private String name;
	private Integer expLevel;
	private Integer trophies;
	private ArenaInfo arena;
	private String role;
	private LocalDateTime lastBattle;

	@Override
	public int compareTo(ClanMemberBattleLogInfo o) {
		// null dates should go first
		if (lastBattle == null) {
			return -1;
		} else if (o == null || o.getLastBattle() == null) {
			return 1;
		} else {
			return lastBattle.compareTo(o.getLastBattle());
		}
	}

}
