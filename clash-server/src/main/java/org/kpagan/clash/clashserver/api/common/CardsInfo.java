package org.kpagan.clash.clashserver.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CardsInfo extends BasicCardInfo {
	public static final int MAX_CARD_LEVEL = 13;
	
	private Integer level;
	private Integer count;
	private Integer correctLevel;
	private Integer maxCardLevel;
	
	public void correctLevels() {
		correctLevel = level + MAX_CARD_LEVEL - maxLevel;
		maxCardLevel = CardsStatisticsTable.getMaxCardsToLevel(level, maxLevel);
	}
}
