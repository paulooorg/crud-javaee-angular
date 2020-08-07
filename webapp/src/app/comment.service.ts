import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Comment } from './comment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  constructor(private http: HttpClient) { }

  save(comment: Comment, taskId: Number): Observable<Number> {
    let commentsUrl = `http://localhost:8080/api/v1/task/${taskId}/comment`;
    return this.http.post<Number>(commentsUrl, comment);
  }

  findById(commentId: Number, taskId: Number): Observable<Comment> {
    let commentsUrl = `http://localhost:8080/api/v1/task/${taskId}/comment/${commentId}`;
    return this.http.get<Comment>(commentsUrl);
  }
}
