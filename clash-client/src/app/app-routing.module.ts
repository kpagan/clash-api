import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IdlePlayersComponent } from './clan/members/idle-players.component';
import { CardsComponent } from './clan/cards/cards.component';
import { ClanDonationsComponent } from './clan/donations/clan-donations.component';

const routes: Routes = [
  { path: 'idle-players', component: IdlePlayersComponent },
  { path: 'member-cards', component: CardsComponent},
  { path: 'member-donations', component: ClanDonationsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
