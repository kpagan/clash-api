import { Component, OnInit } from '@angular/core';
import { CardsService } from './cards.service';
import { PlayerDetailModel } from 'src/app/player/PlayerDetailModel';
import { ClanMemberCardsQuery } from './ClanMemberCardsQuery';
import { MemberCardsResponse } from './MemberCardsResponse';
import { BasicCardModel } from 'src/app/api/model/BasicCardModel';
import { GameCardsResponse } from './GameCardsResponse';
import { FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { ClanBaseComponent } from '../ClanBaseComponent';
@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.scss']
})
export class CardsComponent extends ClanBaseComponent implements OnInit {

  query: ClanMemberCardsQuery = new ClanMemberCardsQuery();
  players: PlayerDetailModel[];
  availableCards: BasicCardModel[];
  filteredCards: Observable<BasicCardModel[]>;
  cardNameControl = new FormControl('', [Validators.required]);

  cardNumberControl = new FormControl('', [
    Validators.required,
    Validators.pattern('[0-9]*'),
  ]);

  constructor(private cardsService: CardsService, protected cookieService: CookieService) {
    super(cookieService);
  }


  ngOnInit() {
    super.ngOnInit();
    this.cardsService.getAllCards().subscribe((response: GameCardsResponse) => {
      this.availableCards = response.info.items;
      this.filteredCards = this.cardNameControl.valueChanges
        .pipe(
          startWith(''),
          map(value => this._filter(value))
        );
    });
  }


  private _filter(value: string): BasicCardModel[] {
    const filterValue = value.toLowerCase();
    return this.availableCards.filter(card => card.name.toLowerCase().includes(filterValue));
  }

  search() {
    super.search();
    this.cardsService.getMemberCards(this.clanTag, this.query).subscribe((response: MemberCardsResponse) => {
      this.players = response.players;
    });
  }

}
