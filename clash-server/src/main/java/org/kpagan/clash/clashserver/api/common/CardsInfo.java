package org.kpagan.clash.clashserver.api.common;

import lombok.Data;

@Data
public class CardsInfo {
	public static final int MAX_CARD_LEVEL = 13;
	
	private String name;
	private Integer level;
	private Integer maxLevel;
	private Integer count;
	private IconUrlInfo iconUrls;
	private Integer correctLevel;
	private Integer maxCardLevel;
	
	public void correctLevels() {
		correctLevel = level + MAX_CARD_LEVEL - maxLevel;
		maxCardLevel = CardsStatisticsTable.getMaxCardsToLevel(level, maxLevel);
	}
}
