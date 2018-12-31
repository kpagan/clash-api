import { ClanBaseModel } from './ClanBaseModel';
import { LeagueStatisticsModel } from './LeagueStatisticsModel';
import { AchievementsModel } from './AchievementsModel';
import { CardsModel } from './CardsModel';
import { FavouriteCardModel } from './FavouriteCardModel';
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
    currentFavouriteCard: FavouriteCardModel;
}
