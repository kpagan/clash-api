import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/api/BaseService';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from '../../api/message/message.service';
import { Observable } from 'rxjs';
import { IdleClanMemberResponse } from './IdleClanMemberResponse';

@Injectable({
  providedIn: 'root'
})
export class MemberService extends BaseService {

  private url = '/idle-clan-member/';

  constructor(protected http: HttpClient, protected messageService: MessageService) {
    super(http, messageService);
  }

  getIdlePlayers(clanTag: string): Observable<IdleClanMemberResponse> {
    return this.http.get<IdleClanMemberResponse>(this.baseUrl + this.url + clanTag)
      .pipe(
        catchError(this.handleError('getIdlePlayers', undefined))
      );
  }

}
