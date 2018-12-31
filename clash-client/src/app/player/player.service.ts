import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from '../api/message/message.service';
import { Observable } from 'rxjs';
import { PlayerDetailModel } from './PlayerDetailModel';
import { BaseService } from '../api/BaseService';

@Injectable({
  providedIn: 'root'
})
export class PlayerService extends BaseService {

  private url = '/players/%239CV8JVUQR';

  constructor(protected http: HttpClient, protected messageService: MessageService) {
    super(http, messageService);
  }

  getPlayer(): Observable<PlayerDetailModel> {
    return this.http.get<PlayerDetailModel>(this.baseUrl + this.url)
      .pipe(
        catchError(this.handleError('getPlayer', undefined))
      );
  }

}
