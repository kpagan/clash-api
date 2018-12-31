import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IdlePlayersComponent } from './clan/members/idle-players.component';

const routes: Routes = [
  { path: 'idle-players', component: IdlePlayersComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
