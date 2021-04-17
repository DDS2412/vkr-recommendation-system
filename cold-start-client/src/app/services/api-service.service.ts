import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {ColdStartEventsDto} from '../models/cold.start.events.dto';
import {ColdStartAnswerDto} from '../models/cold.start.answer.dto';

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {
  public URL: string;

  constructor(private http: HttpClient) {
    this.URL = 'http://3.65.178.42:8082/vkr/coldstart';
  }

  public getSurveyEvents(): Observable<any>{
    return this.http.get<ColdStartEventsDto>(this.URL + '/events')
      .pipe(map((res) => res || {}), catchError(this.handleError));
  }

  // tslint:disable-next-line:typedef
  public setAnswersForSurvey(coldStartAnswers: ColdStartAnswerDto[], userId: number){
    this.http.post(this.URL + '/all/answers/user/' + userId, coldStartAnswers).subscribe();
  }

  // tslint:disable-next-line:typedef
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }

    return throwError(error);
  }
}
