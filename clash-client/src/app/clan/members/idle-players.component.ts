import { Component, OnInit, ViewChild } from '@angular/core';
import { MemberService } from './member.service';
import { ClanMemberModel } from './ClanMemberModel';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { IdlePlayersDataSource } from './idle-players-datasource';
import { ClanBaseComponent } from '../ClanBaseComponent';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-idle-players',
  templateUrl: './idle-players.component.html',
  styleUrls: ['./idle-players.component.scss']
})
export class IdlePlayersComponent extends ClanBaseComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  columns = [
    { columnDef: 'tag', header: 'Tag', cell: (row: ClanMemberModel) => `${row.tag}` },
    { columnDef: 'name', header: 'Name', cell: (row: ClanMemberModel) => `${row.name}` },
    { columnDef: 'expLevel', header: 'Level', cell: (row: ClanMemberModel) => `${row.expLevel}` },
    { columnDef: 'trophies', header: 'Trophies', cell: (row: ClanMemberModel) => `${row.trophies}` },
    { columnDef: 'arena', header: 'Arena', cell: (row: ClanMemberModel) => `${row.arena.name}` },
    { columnDef: 'role', header: 'Role', cell: (row: ClanMemberModel) => `${row.role}` },
  ];

  displayedColumns = this.columns.map(x => x.columnDef);
  dataSource: IdlePlayersDataSource;

  constructor(private memberService: MemberService, protected cookieService: CookieService) {
    super(cookieService);
  }

  ngOnInit() {
    super.ngOnInit();
    this.dataSource = new IdlePlayersDataSource(this.memberService);
    // this.memberService.getIdlePlayers('P9R9282L')
    //   .subscribe(players => {
    //     this.dataSource = new MatTableDataSource<ClanMemberModel>(players.items);
    //     this.dataSource.sort = this.sort;
    //     this.dataSource.paginator = this.paginator;
    //   });
  }

  search() {
    super.search();
    this.dataSource.loadIdlePlayers(this.clanTagControl.value);
  }

}
