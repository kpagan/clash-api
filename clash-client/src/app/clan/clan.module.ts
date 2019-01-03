import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MembersModule } from './members/members.module';
import { CardsModule } from './cards/cards.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MembersModule,
    CardsModule
  ]
})
export class ClanModule { }
