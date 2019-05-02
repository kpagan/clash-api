package org.kpagan.clash.clashserver.api.player;

import java.util.List;

import org.kpagan.clash.clashserver.api.common.ArenaInfo;
import org.kpagan.clash.clashserver.api.common.CardsInfo;
import org.kpagan.clash.clashserver.api.common.ClanBaseInfo;
import org.kpagan.clash.clashserver.api.common.BasicCardInfo;

import lombok.Data;

@Data
public class PlayerDetailsInfo {
	private String tag;
	private String name;
	private Integer expLevel;
	private Integer trophies;
	private ArenaInfo arena;
	private Integer bestTrophies;
	private Integer wins;
	private Integer losses;
	private Integer battleCount;
	private Integer threeCrownWins;
	private Integer challengeCardsWon;
	private Integer challengeMaxWins;
	private Integer tournamentCardsWon;
	private Integer tournamentBattleCount;
	private String role;
	private Integer donations;
	private Integer donationsReceived;
	private Integer totalDonations;
	private Integer warDayWins;
	private Integer clanCardsCollected;
	private ClanBaseInfo clan;
	private LeagueStatisticsInfo leagueStatistics;
	private List<AchievementsInfo> achievements;
	private List<CardsInfo> cards;
	private List<CardsInfo> currentDeck;
	private BasicCardInfo currentFavouriteCard;
	
	/**
	 * This is the number of cards that the player has that are queried in the 'trade cards' functionality
	 */
	private Integer desiredCardCount;
}
