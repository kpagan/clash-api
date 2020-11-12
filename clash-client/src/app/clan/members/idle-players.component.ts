import { Component, OnInit, ViewChild } from '@angular/core';
import { MemberService } from './member.service';
import { ClanMemberBattleLogModel } from './ClanMemberBattleLogModel';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
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
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  columns = [
    { columnDef: 'tag', header: 'Tag', cell: (row: ClanMemberBattleLogModel) => `${row.tag}` },
    { columnDef: 'name', header: 'Name', cell: (row: ClanMemberBattleLogModel) => `${row.name}` },
    { columnDef: 'expLevel', header: 'Level', cell: (row: ClanMemberBattleLogModel) => `${row.expLevel}` },
    { columnDef: 'trophies', header: 'Trophies', cell: (row: ClanMemberBattleLogModel) => `${row.trophies}` },
    { columnDef: 'arena', header: 'Arena', cell: (row: ClanMemberBattleLogModel) => `${row.arena.name}` },
    { columnDef: 'role', header: 'Role', cell: (row: ClanMemberBattleLogModel) => `${row.role}` },
    { columnDef: 'lastBattle', header: 'Last Battle', cell: (row: ClanMemberBattleLogModel) => `${row.lastBattle}` },
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
    //     this.dataSource = new MatTableDataSource<ClanMemberBattleLogModel>(players.items);
    //     this.dataSource.sort = this.sort;
    //     this.dataSource.paginator = this.paginator;
    //   });
  }

  search() {
    super.search();
    this.dataSource.loadIdlePlayers(this.clanTagControl.value);
  }

}
