import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardsComponent } from './cards.component';
import {MatProgressSpinnerModule, MatCardModule} from '@angular/material';
import { CardsService } from './cards.service';

@NgModule({
  declarations: [CardsComponent],
  imports: [
    CommonModule,
    MatProgressSpinnerModule,
    MatCardModule,
  ],
  providers: [CardsService]
})
export class CardsModule { }
