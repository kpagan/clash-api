import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClanDonationsComponent } from './clan-donations.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { DonationsService } from './donations.service';
import { AppMaterialModule } from 'src/app/app.material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [ClanDonationsComponent],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    AppMaterialModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [DonationsService]
})
export class DonationsModule { }
