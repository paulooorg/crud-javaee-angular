import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Status } from './status';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatusService {
  private API_URL= environment.API_URL;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getStatuses(): Observable<Status[]> {
    let statusUrl = `${this.API_URL}/api/v1/taskstatus`;
    return this.http.get<Status[]>(statusUrl);
  }
}
