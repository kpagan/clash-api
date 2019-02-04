import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';

import { ClashProgressBarComponent } from './clash-progress-bar.component';
import { AppMaterialModule } from '../app.material.module';

@NgModule({
  declarations: [ClashProgressBarComponent],
  imports: [
    CommonModule,
    AppMaterialModule,
    LoadingBarHttpClientModule,
  ],
  exports: [ClashProgressBarComponent]
})
export class ClashProgressBarModule { }
