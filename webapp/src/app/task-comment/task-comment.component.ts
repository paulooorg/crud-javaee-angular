import { Component, OnInit, Input } from '@angular/core';
import { Comment } from '../comment';
import { CommentService } from '../comment.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-task-comment',
  templateUrl: './task-comment.component.html',
  styleUrls: ['./task-comment.component.css']
})
export class TaskCommentComponent implements OnInit {
  @Input() comments: Comment[];

  @Input() taskId: Number;

  constructor(private commentService: CommentService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  add(description: string) {
    const newComment = {description: description} as Comment;
    this.commentService.save(newComment, this.taskId).subscribe(commentId => {
      this.commentService.findById(commentId, this.taskId).subscribe(comment => this.comments.push(comment));
      this.snackBar.open(`Comment saved!`, 'Close', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
    });
  }
}
