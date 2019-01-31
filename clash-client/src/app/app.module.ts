import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PlayerModule } from './player/player-module';
import { ClanModule } from './clan/clan.module';
import { MessageComponent } from './api/message/message.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { TestTableComponent } from './test-table/test-table.component';
import { AppMaterialModule } from './app.material.module';

@NgModule({
  declarations: [
    AppComponent,
    MessageComponent,
    TestTableComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    LayoutModule,
    AppRoutingModule,
    HttpClientModule,
    AppMaterialModule,
    PlayerModule,
    ClanModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
