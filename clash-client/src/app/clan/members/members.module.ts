import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberService } from './member.service';
import { IdlePlayersComponent } from './idle-players.component';
import {MatTableModule} from '@angular/material';
import { MatSortModule, MatPaginatorModule, MatProgressSpinnerModule } from '@angular/material';

@NgModule({
  declarations: [IdlePlayersComponent],
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule
  ],
  providers: [MemberService]
})
export class MembersModule { }
