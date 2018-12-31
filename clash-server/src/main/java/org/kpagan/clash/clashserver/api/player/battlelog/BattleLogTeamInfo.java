package org.kpagan.clash.clashserver.api.player.battlelog;

import java.util.List;

import org.kpagan.clash.clashserver.api.common.CardsInfo;
import org.kpagan.clash.clashserver.api.common.ClanBaseInfo;

import lombok.Data;

@Data
public class BattleLogTeamInfo {
	private String tag;
	private String name;
	private Integer startingTrophies;
	private Integer trophyChange;
	private Integer crowns;
	private ClanBaseInfo clan;
	private List<CardsInfo> cards;
}
