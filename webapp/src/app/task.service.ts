import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, concat } from 'rxjs';
import { catchError, map, tap, take } from 'rxjs/operators';
import { Task } from './task';
import { Page } from './page';
import { MatSnackBar } from '@angular/material/snack-bar';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private API_URL= environment.API_URL;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private snackBar: MatSnackBar) { }

  findById(id: Number): Observable<Task> {
    let taskUrl = `${this.API_URL}/api/v1/task/${id}`;
    return this.http.get<Task>(taskUrl);
  }

  getTasks(page: Number, pageSize: Number): Observable<Page<Task>> {
    let tasksUrl = `${this.API_URL}/api/v1/task?per_page=${pageSize}&page=${page}`;
    return this.http.get<Page<Task>>(tasksUrl);
  }

  search(term: string, page: Number, pageSize: Number): Observable<Page<Task>> {
    let tasksUrl = `${this.API_URL}/api/v1/task/search/${term}?per_page=${pageSize}&page=${page}`;
    return this.http.get<Page<Task>>(tasksUrl);
  }

  save(task: Task): Observable<Task> {
    let taskUrl = `${this.API_URL}/api/v1/task`;
    if (task.id) {
      // update
      return this.http.put<Task>(taskUrl + `/${task.id}`, task);
    } else {
      // create
      return this.http.post<Task>(taskUrl, task).pipe(catchError(error => this.handleError<Task>(error)));
    }
  }

  delete(task: Task) {
    let taskUrl = `${this.API_URL}/api/v1/task/${task.id}`;
    return this.http.delete(taskUrl);
  }

  private handleError<T>(error, result?: T) {
    console.log(error);
    this.snackBar.open(error.error.message, 'Close', {
      duration: 8000,
      horizontalPosition: 'center',
      verticalPosition: 'top'
    });
    return (error: any): Observable<T> => {
      return of(result as T);
    }
  }
}
