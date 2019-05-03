package org.kpagan.clash.clashserver.api.common;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CardsStatisticsTableTest {
	

	@Parameter(0)
	public int level;

	@Parameter(1)
	public int maxLevel;

	@Parameter(2)
	public int expectedMaxCardsToLevel;

	@Parameters(name = "{index}: level: {0}, maxLevel: {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(
			new Object[][] { 
				{ 1, 13, 2 },
				{ 2, 13, 4 },
				{ 3, 13, 10 },
				{ 4, 13, 20 },
				{ 5, 13, 50 },
				{ 6, 13, 100 },
				{ 7, 13, 200 },
				{ 8, 13, 400 },
				{ 9, 13, 800 },
				{ 10, 13, 1000 },
				{ 11, 13, 2000 },
				{ 12, 13, 5000 },
				{ 13, 13, 0 },
				{ 1, 11, 2 },
				{ 2, 11, 4 },
				{ 3, 11, 10 },
				{ 4, 11, 20 },
				{ 5, 11, 50 },
				{ 6, 11, 100 },
				{ 7, 11, 200 },
				{ 8, 11, 400 },
				{ 9, 11, 800 },
				{ 10, 11, 1000 },
				{ 11, 11, 0 },
				{ 1, 8, 2 },
				{ 2, 8, 4 },
				{ 3, 8, 10 },
				{ 4, 8, 20 },
				{ 5, 8, 50 },
				{ 6, 8, 100 },
				{ 7, 8, 200 },
				{ 8, 8, 0 },
				{ 1, 5, 2 },
				{ 2, 5, 4 },
				{ 3, 5, 10 },
				{ 4, 5, 20 },
				{ 5, 5, 0 },
		});
	}
	
	@Test
	public void testGetMaxCardsToLevel() {
		int maxCardsToLevel = CardsStatisticsTable.getMaxCardsToLevel(level, maxLevel);
		assertEquals(maxCardsToLevel, expectedMaxCardsToLevel);
	}
	
}
