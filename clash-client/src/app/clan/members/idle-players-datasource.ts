import { DataSource, CollectionViewer } from '@angular/cdk/collections';
import { MatPaginator, MatSort } from '@angular/material';
import { map, catchError, finalize } from 'rxjs/operators';
import { Observable, of as observableOf, merge, BehaviorSubject, of } from 'rxjs';
import { ClanMemberModel } from './ClanMemberModel';
import { MemberService } from './member.service';
import { IdleClanMemberResponse } from './IdleClanMemberResponse';

/**
 * Data source for the TestTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class IdlePlayersDataSource extends DataSource<ClanMemberModel> {
  private idlePlayersSubject = new BehaviorSubject<ClanMemberModel[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  data: ClanMemberModel[];

  public loading$ = this.loadingSubject.asObservable();

  constructor(private memberService: MemberService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<ClanMemberModel[]> {
    return this.idlePlayersSubject.asObservable();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  // connect(): Observable<ClanMemberModel[]> {
  //   // Combine everything that affects the rendered data into one update
  //   // stream for the data-table to consume.
  //   const dataMutations = [
  //     this.memberService.getIdlePlayers('P9R9282L'),
  //     this.paginator.page,
  //     this.sort.sortChange
  //   ];

  // Set the paginator's length
  // this.paginator.length = this.data.length;

  // return merge(...dataMutations).pipe(map(() => {
  //   return this.getPagedData(this.getSortedData([...this.data]));
  // }));
  //   }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {
    this.idlePlayersSubject.complete();
    this.loadingSubject.complete();
  }

  loadIdlePlayers(clanTag: string) {

    this.loadingSubject.next(true);

    this.memberService.getIdlePlayers(clanTag).pipe(
      catchError(() => of(new IdleClanMemberResponse())),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe((players: IdleClanMemberResponse) => this.idlePlayersSubject.next(players.idlePlayers));
  }

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  // private getPagedData(data: ClanMemberModel[]) {
  //   const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
  //   return data.splice(startIndex, this.paginator.pageSize);
  // }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  // private getSortedData(data: ClanMemberModel[]) {
  //   if (!this.sort.active || this.sort.direction === '') {
  //     return data;
  //   }

  //   return data.sort((a, b) => {
  //     const isAsc = this.sort.direction === 'asc';
  //     switch (this.sort.active) {
  //       case 'name': return compare(a.name, b.name, isAsc);
  //       case 'tag': return compare(a.tag, b.tag, isAsc);
  //       default: return 0;
  //     }
  //   });
  // }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
