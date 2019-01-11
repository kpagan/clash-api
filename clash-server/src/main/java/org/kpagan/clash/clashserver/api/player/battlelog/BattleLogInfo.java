package org.kpagan.clash.clashserver.api.player.battlelog;

import java.time.LocalDateTime;
import java.util.List;

import org.kpagan.clash.clashserver.api.common.ArenaInfo;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class BattleLogInfo {
	
	@Id
	private String playerTag;
	private String type;
	private String battleTime;
	private ArenaInfo arena;
	private GameModeInfo gameMode;
	private String deckSelection;
	private List<BattleLogTeamInfo> team;
	private List<BattleLogTeamInfo> opponent;
	
	private LocalDateTime timestamp;
}
