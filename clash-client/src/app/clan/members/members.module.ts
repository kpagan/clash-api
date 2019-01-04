import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberService } from './member.service';
import { IdlePlayersComponent } from './idle-players.component';
import { AppMaterialModule } from 'src/app/app.material.module';

@NgModule({
  declarations: [IdlePlayersComponent],
  imports: [
    CommonModule,
    AppMaterialModule
  ],
  providers: [MemberService]
})
export class MembersModule { }
