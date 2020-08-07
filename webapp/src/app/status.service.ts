import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Status } from './status';

@Injectable({
  providedIn: 'root'
})
export class StatusService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getStatuses(): Observable<Status[]> {
    let statusUrl = `http://localhost:8080/api/v1/taskstatus`;
    return this.http.get<Status[]>(statusUrl);
  }
}
