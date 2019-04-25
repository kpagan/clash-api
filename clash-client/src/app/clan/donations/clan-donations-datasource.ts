import { DataSource } from '@angular/cdk/collections';
import { MatSort } from '@angular/material';
import { map, catchError } from 'rxjs/operators';
import { Observable, merge, BehaviorSubject, of } from 'rxjs';
import { ClanMemberDonationsModel } from './ClanMemberDonationsModel';
import { DonationsService } from './donations.service';
import { IdleClanMemberResponse } from '../members/IdleClanMemberResponse';
import { ClanMemberDonationsResponse } from './ClanMemberDonationsResponse';

/**
 * Data source for the Clan Donations view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class ClanDonationsDataSource extends DataSource<ClanMemberDonationsModel> {
    donationSubject = new BehaviorSubject<ClanMemberDonationsModel[]>([]);

    constructor(private donationsService: DonationsService, private sort: MatSort) {
        super();
    }

    /**
     * Connect this data source to the table. The table will only update when
     * the returned stream emits new items.
     * @returns A stream of the items to be rendered.
     */
    connect(): Observable<ClanMemberDonationsModel[]> {
        // Combine everything that affects the rendered data into one update
        // stream for the data-table to consume.
        const dataMutations = [
            this.donationSubject.asObservable(),
            this.sort.sortChange
        ];

        return merge(...dataMutations).pipe(map(() => {
            return this.getSortedData([...this.donationSubject.value]);
        }));
    }

    /**
     *  Called when the table is being destroyed. Use this function, to clean up
     * any open connections or free any held resources that were set up during connect.
     */
    disconnect() { }

    loadMemberDonations(clanTag: string) {
        this.donationsService.getMemberDonations(clanTag).pipe(
            catchError(() => of(new IdleClanMemberResponse()))
        ).subscribe((members: ClanMemberDonationsResponse) => this.donationSubject.next(members.members));
    }

    /**
     * Sort the data (client-side). If you're using server-side sorting,
     * this would be replaced by requesting the appropriate data from the server.
     */
    private getSortedData(data: ClanMemberDonationsModel[]) {
        if (!this.sort.active || this.sort.direction === '') {
            return data;
        }

        return data.sort((a, b) => {
            const isAsc = this.sort.direction === 'asc';
            if (isNumber(a[this.sort.active]) && isNumber(b[this.sort.active])) {
                return compare(+a[this.sort.active], +b[this.sort.active], isAsc);
            } else {
                return compare(a[this.sort.active], b[this.sort.active], isAsc);
            }
        });
    }
}

/** Simple sort comparator for example (for client-side sorting). */
function compare(a: any, b: any, isAsc: boolean) {
    if (a === null) {
        return 1;
    } else if (b === null) {
        return -1;
    }
    if (isNaN(a) && isNaN(b)) {
        return (a.toLowerCase() < b.toLowerCase() ? -1 : 1) * (isAsc ? 1 : -1);
    } else {
        return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }
}

function isNumber(a: any): boolean {
    return a === null || a === undefined ? false : !isNaN(a);
}
