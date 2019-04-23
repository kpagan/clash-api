import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MembersModule } from './members/members.module';
import { CardsModule } from './cards/cards.module';
import { DonationsModule } from './donations/donations.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MembersModule,
    CardsModule,
    DonationsModule
  ]
})
export class ClanModule { }
