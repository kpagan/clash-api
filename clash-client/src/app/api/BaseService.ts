import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message/message.service';
import { Observable, of } from 'rxjs';

const httpOptions = {
    headers: new HttpHeaders({
        'Accept': 'application/json',
        // tslint:disable-next-line:max-line-length
        'authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjdlNThhNDAyLTk1ZWItNGQ1OS1iODBjLWQ0YTM4OWVkZjQ2YyIsImlhdCI6MTU0NDc4NjM2NCwic3ViIjoiZGV2ZWxvcGVyL2ZmODYzYzA1LWFhZWYtNDljOC04ODFkLTdjMzQ5NWU0YTFkZSIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIyMTcuMTE3LjM0LjE2Il0sInR5cGUiOiJjbGllbnQifV19.FHIkf1WcvdIGW2B1pkwWuL20o-DWnlc75RiN4T9FPgxqxDPMrAnB_9piZdMPkpxyzBV56H-7am_zpdr-9Yf5kg'
    })
};

export class BaseService {

    protected baseUrl = '/api';

    constructor(protected http: HttpClient, protected messageService: MessageService) { }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
    */
    protected handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead

            // TODO: better job of transforming error for user consumption
            this.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

    private log(message: string) {
        this.messageService.add(`PlayerService: ${message}`);
    }

}
