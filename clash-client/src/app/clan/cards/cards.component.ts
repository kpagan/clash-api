import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { CardsService } from './cards.service';
import { PlayerDetailModel } from 'src/app/player/PlayerDetailModel';
import { ClanMemberCardsQuery } from './ClanMemberCardsQuery';
import { MemberCardsResponse } from './MemberCardsResponse';
import { BasicCardModel } from 'src/app/api/model/BasicCardModel';
import { GameCardsResponse } from './GameCardsResponse';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { ErrorStateMatcher } from '@angular/material';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.scss']
})
export class CardsComponent implements OnInit {

  query: ClanMemberCardsQuery = new ClanMemberCardsQuery();
  players: PlayerDetailModel[];
  availableCards: BasicCardModel[];
  selectedCard: string;
  numberOfCards: number;
  filteredCards: Observable<BasicCardModel[]>;
  cardNameControl = new FormControl('', [Validators.required]);

  cardNumberControl = new FormControl('', [
    Validators.required,
    Validators.pattern('[0-9]*'),
  ]);

  matcher = new MyErrorStateMatcher();

  constructor(private cardsService: CardsService) { }


  ngOnInit() {
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
    this.query.card = this.selectedCard;
    this.query.no = this.numberOfCards;
    this.cardsService.getMemberCards('P9R9282L', this.query).subscribe((response: MemberCardsResponse) => {
      this.players = response.players;
    });
  }

}
