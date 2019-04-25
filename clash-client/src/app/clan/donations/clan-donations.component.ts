import { Component, OnInit, ViewChild } from '@angular/core';
import { ClanBaseComponent } from '../ClanBaseComponent';
import { CookieService } from 'ngx-cookie-service';
import { DonationsService } from './donations.service';
import { ClanMemberDonationsModel } from './ClanMemberDonationsModel';
import { MatSort } from '@angular/material';
import { ClanDonationsDataSource } from './clan-donations-datasource';

@Component({
  selector: 'app-clan-donations',
  templateUrl: './clan-donations.component.html',
  styleUrls: ['./clan-donations.component.scss']
})
export class ClanDonationsComponent extends ClanBaseComponent implements OnInit {
  @ViewChild(MatSort) sort: MatSort;

  columns = [
    { columnDef: 'tag', header: 'Tag', cell: (row: ClanMemberDonationsModel) => `${row.tag}` },
    { columnDef: 'name', header: 'Name', cell: (row: ClanMemberDonationsModel) => `${row.name}` },
    { columnDef: 'memberSince', header: 'Member since', cell: (row: ClanMemberDonationsModel) => `${row.memberSince}` },
    {
      columnDef: 'donatedFromJoinDay', header: 'Donated from join day',
      cell: (row: ClanMemberDonationsModel) => `${row.donatedFromJoinDay}`
    },
    {
      columnDef: 'receivedFromJoinDay', header: 'Received from join day',
      cell: (row: ClanMemberDonationsModel) => `${row.receivedFromJoinDay}`
    },
    {
      columnDef: 'averageWeeklyDonations', header: 'Average weekly donations',
      cell: (row: ClanMemberDonationsModel) => `${row.averageWeeklyDonations}`
    },
    {
      columnDef: 'weekDonationsSoFar', header: 'Weekly donations so far',
      cell: (row: ClanMemberDonationsModel) => `${row.weekDonationsSoFar}`
    },
    {
      columnDef: 'weekDonationsReceivedSoFar', header: 'Weekly donations received so far',
      cell: (row: ClanMemberDonationsModel) => `${row.weekDonationsReceivedSoFar}`
    },
    { columnDef: 'timesRejoined', header: 'Rejoined', cell: (row: ClanMemberDonationsModel) => `${row.timesRejoined}` },
    { columnDef: 'leftClan', header: 'Left clan', cell: (row: ClanMemberDonationsModel) => `${row.leftClan}` },
    { columnDef: 'remarks', header: 'Remarks', cell: (row: ClanMemberDonationsModel) => `${row.remarks}` },
  ];

  displayedColumns = this.columns.map(x => x.columnDef);
  dataSource: ClanDonationsDataSource;

  constructor(private donationsService: DonationsService, protected cookieService: CookieService) {
    super(cookieService);
  }

  ngOnInit() {
    super.ngOnInit();
    this.dataSource = new ClanDonationsDataSource(this.donationsService, this.sort);
  }

  search() {
    super.search();
    this.dataSource.loadMemberDonations(this.clanTagControl.value);
  }

}
