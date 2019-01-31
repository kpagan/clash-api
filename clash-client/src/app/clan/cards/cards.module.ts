import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardsComponent } from './cards.component';
import { CardsService } from './cards.service';
import { AppMaterialModule } from 'src/app/app.material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

@NgModule({
  declarations: [CardsComponent],
  imports: [
    CommonModule,
    FormsModule,
    AppMaterialModule,
    ReactiveFormsModule,

  ],
  providers: [CardsService]
})
export class CardsModule { }
