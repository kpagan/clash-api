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

    this.cardsService.getMemberCards('P9R9282L', this.query).subscribe((response: MemberCardsResponse) => {
      this.players = response.players;
    });
  }

}
