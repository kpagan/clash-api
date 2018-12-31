import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PlayerModule } from './player/player-module';
import { ClanModule } from './clan/clan.module';
import { MessageComponent } from './api/message/message.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatGridListModule,
        MatCardModule, MatMenuModule, MatTableModule, MatPaginatorModule, MatSortModule,
        MatProgressSpinnerModule } from '@angular/material';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import { TestTableComponent } from './test-table/test-table.component';

@NgModule({
  declarations: [
    AppComponent,
    MessageComponent,
    TestTableComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatProgressSpinnerModule,
    AppRoutingModule,
    HttpClientModule,
    PlayerModule,
    ClanModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
