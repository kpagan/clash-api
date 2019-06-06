import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/api/BaseService';
import { MessageService } from 'src/app/api/message/message.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ClanMemberDonationsResponse } from './ClanMemberDonationsResponse';

@Injectable({
  providedIn: 'root'
})
export class DonationsService extends BaseService {

  private url = '/clan-member-donations/';

  constructor(protected http: HttpClient, protected messageService: MessageService) {
    super(http, messageService);
  }

  getMemberDonations(clanTag: string): Observable<ClanMemberDonationsResponse> {
    return this.http.get<ClanMemberDonationsResponse>(this.baseUrl + this.url + clanTag)
      .pipe(
        catchError(this.handleError('getMemberDonations', undefined))
      );
  }

}
