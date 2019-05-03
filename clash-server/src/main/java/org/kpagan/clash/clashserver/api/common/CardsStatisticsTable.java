package org.kpagan.clash.clashserver.api.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsStatisticsTable {

	public enum CardType {
		COMMON(1), RARE(3), EPIC(6), LEGENDARY(9);

		private int startingLevel;

		CardType(int startingLevel) {
			this.startingLevel = startingLevel;
		}

		public int getStartingLevel() {
			return startingLevel;
		}

		public static CardType ofMaxLevel(int maxLevel) {
			switch (maxLevel) {
			case 13:
				return COMMON;
			case 11:
				return RARE;
			case 8:
				return EPIC;
			case 5:
				return LEGENDARY;
			default:
				throw new IllegalArgumentException("Unknown level " + maxLevel);
			}
		}

	}

	private static final Map<CardType, List<Integer>> LEVELS = new HashMap<CardType, List<Integer>>() {
		private static final long serialVersionUID = 1L;
		{
			put(CardType.COMMON, Arrays.asList(1, 2, 4, 10, 20, 50, 100, 200, 400, 800, 1000, 2000, 5000, 0));
			put(CardType.RARE, Arrays.asList(1, 2, 4, 10, 20, 50, 100, 200, 400, 800, 1000, 0));
			put(CardType.EPIC, Arrays.asList(1, 2, 4, 10, 20, 50, 100, 200, 0));
			put(CardType.LEGENDARY, Arrays.asList(1, 2, 4, 10, 20, 0));
		}
	};

	public static int getMaxCardsToLevel(int level, int maxLevel) {
		return LEVELS.get(CardType.ofMaxLevel(maxLevel)).get(level);
	}

}
