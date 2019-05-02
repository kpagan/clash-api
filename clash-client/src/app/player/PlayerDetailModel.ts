import { ClanBaseModel } from '../api/model/ClanBaseModel';
import { LeagueStatisticsModel } from './LeagueStatisticsModel';
import { AchievementsModel } from './AchievementsModel';
import { CardsModel } from '../api/model/CardsModel';
import { BasicCardModel } from '../api/model/BasicCardModel';
import { ArenaModel } from '../api/model/ArenaModel';

export class PlayerDetailModel {
    tag: string;
    name: string;
    expLevel: number;
    trophies: number;
    arena: ArenaModel;
    bestTrophies: number;
    wins: number;
    losses: number;
    battleCount: number;
    threeCrownWins: number;
    challengeCardsWon: number;
    challengeMaxWins: number;
    tournamentCardsWon: number;
    tournamentBattleCount: number;
    role: string;
    donations: number;
    donationsReceived: number;
    totalDonations: number;
    warDayWins: number;
    clanCardsCollected: number;
    clan: ClanBaseModel;
    leagueStatistics: LeagueStatisticsModel;
    achievements: AchievementsModel[];
    cards: CardsModel[];
    currentDeck: CardsModel[];
    currentFavouriteCard: BasicCardModel;

    desiredCardCount: number;
}
