import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Comment } from './comment';
import { Observable, of, concat } from 'rxjs';
import { catchError, map, tap, take } from 'rxjs/operators';
import { environment } from '../environments/environment';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private API_URL= environment.API_URL;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  constructor(private http: HttpClient, private snackBar: MatSnackBar) { }

  save(comment: Comment, taskId: Number): Observable<Number> {
    let commentsUrl = `${this.API_URL}/api/v1/task/${taskId}/comment`;
    return this.http.post<Number>(commentsUrl, comment).pipe(catchError(error => this.handleError<Comment>(error)));;
  }

  findById(commentId: Number, taskId: Number): Observable<Comment> {
    let commentsUrl = `${this.API_URL}/api/v1/task/${taskId}/comment/${commentId}`;
    return this.http.get<Comment>(commentsUrl);
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
