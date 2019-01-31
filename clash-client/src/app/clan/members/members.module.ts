import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberService } from './member.service';
import { IdlePlayersComponent } from './idle-players.component';
import { AppMaterialModule } from 'src/app/app.material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [IdlePlayersComponent],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    AppMaterialModule
  ],
  providers: [MemberService]
})
export class MembersModule { }
