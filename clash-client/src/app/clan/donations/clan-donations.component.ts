import { Component, OnInit, ViewChild } from '@angular/core';
import { ClanBaseComponent } from '../ClanBaseComponent';
import { CookieService } from 'ngx-cookie-service';
import { DonationsService } from './donations.service';
import { ClanMemberDonationsModel } from './ClanMemberDonationsModel';
import { MatSort } from '@angular/material';
import { ClanDonationsDataSource } from './clan-donations-datasource';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-clan-donations',
  templateUrl: './clan-donations.component.html',
  styleUrls: ['./clan-donations.component.scss']
})
export class ClanDonationsComponent extends ClanBaseComponent implements OnInit {
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  leftMembersToggle: FormControl = new FormControl();

  columns = [
    { columnDef: 'index', header: 'No', cell: (index: number, row: ClanMemberDonationsModel) => `${index + 1}` },
    { columnDef: 'tag', header: 'Tag', cell: (index: number, row: ClanMemberDonationsModel) => `${row.tag}` },
    { columnDef: 'name', header: 'Name', cell: (index: number, row: ClanMemberDonationsModel) => `${row.name}` },
    { columnDef: 'memberSince', header: 'Member since', cell: (index: number, row: ClanMemberDonationsModel) => `${row.memberSince}` },
    {
      columnDef: 'donatedFromJoinDay', header: 'Donated from join day',
      cell: (index: number, row: ClanMemberDonationsModel) => `${row.donatedFromJoinDay}`
    },
    {
      columnDef: 'receivedFromJoinDay', header: 'Received from join day',
      cell: (index: number, row: ClanMemberDonationsModel) => `${row.receivedFromJoinDay}`
    },
    {
      columnDef: 'donatedReceivedFromJoinDayRatio', header: 'Received/Donated ratio',
      cell: (index: number, row: ClanMemberDonationsModel) =>
        `${(Math.round(row.donatedReceivedFromJoinDayRatio * 100) / 100).toFixed(2).replace('\.00', '')}`
    },
    {
      columnDef: 'averageWeeklyDonations', header: 'Average weekly donations',
      cell: (index: number, row: ClanMemberDonationsModel) => `${row.averageWeeklyDonations}`
    },
    {
      columnDef: 'weekDonationsSoFar', header: 'Weekly donations so far',
      cell: (index: number, row: ClanMemberDonationsModel) => `${row.weekDonationsSoFar}`
    },
    {
      columnDef: 'weekDonationsReceivedSoFar', header: 'Weekly donations received so far',
      cell: (index: number, row: ClanMemberDonationsModel) => `${row.weekDonationsReceivedSoFar}`
    },
    {
      columnDef: 'donatedReceivedSoFarRatio', header: 'Received/Donated so far ratio',
      cell: (index: number, row: ClanMemberDonationsModel) =>
        `${(Math.round(row.donatedReceivedSoFarRatio * 100) / 100).toFixed(2).replace('\.00', '')}`
    },
    { columnDef: 'timesRejoined', header: 'Rejoined', cell: (index: number, row: ClanMemberDonationsModel) => `${row.timesRejoined}` },
    { columnDef: 'leftClan', header: 'Left clan', cell: (index: number, row: ClanMemberDonationsModel) => `${row.leftClan}` },
    // { columnDef: 'remarks', header: 'Remarks', cell: (index: number, row: ClanMemberDonationsModel) => `${row.remarks}` },
    { columnDef: 'totalWarDayWins', header: 'Last 10 War day wins',
      cell: (index: number, row: ClanMemberDonationsModel) => `${row.totalWarDayWins}` },
  ];

  displayedColumns = this.columns.map(x => x.columnDef);
  // displayedColumns = ['index'].concat(this.columns.map(x => x.columnDef));
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

  toggleLeftMembers() {
    this.dataSource.toggleFilter(this.leftMembersToggle.value);
  }

}
