import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/api/BaseService';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from '../../api/message/message.service';
import { Observable } from 'rxjs';
import { MemberCardsResponse } from './MemberCardsResponse';
import { ClanMemberCardsQuery } from './ClanMemberCardsQuery';

@Injectable({
  providedIn: 'root'
})
export class CardsService extends BaseService {

  private url = '/clan-member-cards/%23';

  constructor(protected http: HttpClient, protected messageService: MessageService) {
    super(http, messageService);
  }

  getMemberCards(clanTag: string, query: ClanMemberCardsQuery): Observable<MemberCardsResponse> {
    const params = JSON.stringify(query);
    return this.http.get<MemberCardsResponse>(this.baseUrl + this.url + clanTag + '?' + params)
      .pipe(
        catchError(this.handleError('getMemberCards', undefined))
      );
  }
}
