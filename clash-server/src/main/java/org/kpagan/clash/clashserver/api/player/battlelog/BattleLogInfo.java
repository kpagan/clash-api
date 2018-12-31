package org.kpagan.clash.clashserver.api.player.battlelog;

import java.util.List;

import org.kpagan.clash.clashserver.api.common.ArenaInfo;

import lombok.Data;

@Data
public class BattleLogInfo {
	private String type;
	private String battleTime;
	private ArenaInfo arena;
	private GameModeInfo gameMode;
	private String deckSelection;
	private List<BattleLogTeamInfo> team;
	private List<BattleLogTeamInfo> opponent;
}
