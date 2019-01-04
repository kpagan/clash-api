import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardsComponent } from './cards.component';
import { CardsService } from './cards.service';
import { AppMaterialModule } from 'src/app/app.material.module';

@NgModule({
  declarations: [CardsComponent],
  imports: [
    CommonModule,
    AppMaterialModule
  ],
  providers: [CardsService]
})
export class CardsModule { }
