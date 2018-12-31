package org.kpagan.clash.clashserver.api.player;

import lombok.Data;

@Data
public class LeagueStatisticsInfo {
	private SeasonStatisticsInfo currentSeason;
	private SeasonStatisticsInfo previousSeason;
	private SeasonStatisticsInfo bestSeason;
}
