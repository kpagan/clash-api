import { Component, OnInit, ViewChild } from '@angular/core';
import { CardsService } from './cards.service';
import { PlayerDetailModel } from 'src/app/player/PlayerDetailModel';
import { ClanMemberCardsQuery } from './ClanMemberCardsQuery';
import { MemberCardsResponse } from './MemberCardsResponse';


@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.scss']
})
export class CardsComponent implements OnInit {

  query: ClanMemberCardsQuery = new ClanMemberCardsQuery();
  players: PlayerDetailModel[];

  constructor(private cardsService: CardsService) { }


  ngOnInit() {
  }

  search() {
    this.query.card = 'Wizard';
    this.query.no = 50;

    this.players = [
      {
        'tag': '#P2P8RPCC',
        'name': 'themis10',
        'expLevel': null,
        'trophies': null,
        'arena': null,
        'bestTrophies': null,
        'wins': null,
        'losses': null,
        'battleCount': null,
        'threeCrownWins': null,
        'challengeCardsWon': null,
        'challengeMaxWins': null,
        'tournamentCardsWon': null,
        'tournamentBattleCount': null,
        'role': null,
        'donations': null,
        'donationsReceived': null,
        'totalDonations': null,
        'warDayWins': null,
        'clanCardsCollected': null,
        'clan': null,
        'leagueStatistics': null,
        'achievements': null,
        'cards': null,
        'currentDeck': [{
          'name': 'Wizard',
          'level': 7,
          'maxLevel': 11,
          'count': 53,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/Mej7vnv4H_3p_8qPs_N6_GKahy6HDr7pU7i9eTHS84U.png'
          },
          'correctLevel': 9,
          'maxCardLevel': 200
        },
        {
          'name': 'Princess',
          'level': 1,
          'maxLevel': 5,
          'count': 1,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/bAwMcqp9EKVIKH3ZLm_m0MqZFSG72zG-vKxpx8aKoVs.png'
          },
          'correctLevel': 9,
          'maxCardLevel': 2
        },
        {
          'name': 'Skeleton Army',
          'level': 4,
          'maxLevel': 8,
          'count': 1,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/fAOToOi1pRy7svN2xQS6mDkhQw2pj9m_17FauaNqyl4.png'
          },
          'correctLevel': 9,
          'maxCardLevel': 20
        },
        {
          'name': 'Executioner',
          'level': 3,
          'maxLevel': 8,
          'count': 9,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/9XL5BP2mqzV8kza6KF8rOxrpCZTyuGLp2l413DTjEoM.png'
          },
          'correctLevel': 8,
          'maxCardLevel': 10
        },
        {
          'name': 'Mini P.E.K.K.A',
          'level': 8,
          'maxLevel': 11,
          'count': 52,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/Fmltc4j3Ve9vO_xhHHPEO3PRP3SmU2oKp2zkZQHRZT4.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 400
        },
        {
          'name': 'Valkyrie',
          'level': 8,
          'maxLevel': 11,
          'count': 21,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/0lIoYf3Y_plFTzo95zZL93JVxpfb3MMgFDDhgSDGU9A.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 400
        },
        {
          'name': 'Elite Barbarians',
          'level': 10,
          'maxLevel': 13,
          'count': 1208,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/C88C5JH_F3lLZj6K-tLcMo5DPjrFmvzIb1R2M6xCfTE.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 1000
        },
        {
          'name': 'Prince',
          'level': 4,
          'maxLevel': 8,
          'count': 5,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/3JntJV62aY0G1Qh6LIs-ek-0ayeYFY3VItpG7cb9I60.png'
          },
          'correctLevel': 9,
          'maxCardLevel': 20
        }],
        'currentFavouriteCard': {
          'name': 'Guards',
          'id': 26000025,
          'maxLevel': 8,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/1ArKfLJxYo6_NU_S9cAeIrfbXqWH0oULVJXedxBXQlU.png'
          }
        }
      },
      {
        'tag': '#99JVP88R2',
        'name': 'aggelos',
        'expLevel': null,
        'trophies': null,
        'arena': null,
        'bestTrophies': null,
        'wins': null,
        'losses': null,
        'battleCount': null,
        'threeCrownWins': null,
        'challengeCardsWon': null,
        'challengeMaxWins': null,
        'tournamentCardsWon': null,
        'tournamentBattleCount': null,
        'role': null,
        'donations': null,
        'donationsReceived': null,
        'totalDonations': null,
        'warDayWins': null,
        'clanCardsCollected': null,
        'clan': null,
        'leagueStatistics': null,
        'achievements': null,
        'cards': null,
        'currentDeck': [{
          'name': 'Golem',
          'level': 6,
          'maxLevel': 8,
          'count': 5,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/npdmCnET7jmVjJvjJQkFnNSNnDxYHDBigbvIAloFMds.png'
          },
          'correctLevel': 11,
          'maxCardLevel': 100
        },
        {
          'name': 'Bats',
          'level': 10,
          'maxLevel': 13,
          'count': 366,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/EnIcvO21hxiNpoI-zO6MDjLmzwPbq8Z4JPo2OKoVUjU.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 1000
        },
        {
          'name': 'Goblin Barrel',
          'level': 5,
          'maxLevel': 8,
          'count': 5,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/CoZdp5PpsTH858l212lAMeJxVJ0zxv9V-f5xC8Bvj5g.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 50
        },
        {
          'name': 'Royal Ghost',
          'level': 1,
          'maxLevel': 5,
          'count': 1,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/3En2cz0ISQAaMTHY3hj3rTveFN2kJYq-H4VxvdJNvCM.png'
          },
          'correctLevel': 9,
          'maxCardLevel': 2
        },
        {
          'name': 'Mega Knight',
          'level': 2,
          'maxLevel': 5,
          'count': 0,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/O2NycChSNhn_UK9nqBXUhhC_lILkiANzPuJjtjoz0CE.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 4
        },
        {
          'name': 'Skeleton Army',
          'level': 5,
          'maxLevel': 8,
          'count': 0,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/fAOToOi1pRy7svN2xQS6mDkhQw2pj9m_17FauaNqyl4.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 50
        },
        {
          'name': 'Wizard',
          'level': 8,
          'maxLevel': 11,
          'count': 123,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/Mej7vnv4H_3p_8qPs_N6_GKahy6HDr7pU7i9eTHS84U.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 400
        },
        {
          'name': 'Zap',
          'level': 10,
          'maxLevel': 13,
          'count': 565,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/7dxh2-yCBy1x44GrBaL29vjqnEEeJXHEAlsi5g6D1eY.png'
          },
          'correctLevel': 10,
          'maxCardLevel': 1000
        }],
        'currentFavouriteCard': {
          'name': 'Wizard',
          'id': 26000017,
          'maxLevel': 11,
          'iconUrls': {
            'medium': 'https://api-assets.clashroyale.com/cards/300/Mej7vnv4H_3p_8qPs_N6_GKahy6HDr7pU7i9eTHS84U.png'
          }
        }
      }
    ];

    // this.cardsService.getMemberCards('P9R9282L', this.query).subscribe((response: MemberCardsResponse) => {
    //   this.players = response.players;
    // });
  }

}
