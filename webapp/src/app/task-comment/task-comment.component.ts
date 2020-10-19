import { Component, OnInit, Input } from '@angular/core';
import { Comment } from '../comment';
import { CommentService } from '../comment.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-task-comment',
  templateUrl: './task-comment.component.html',
  styleUrls: ['./task-comment.component.css']
})
export class TaskCommentComponent implements OnInit {
  @Input() comments: Comment[];

  @Input() taskId: Number;

  public Editor = ClassicEditor;

  public comment: String = "";

  constructor(private commentService: CommentService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  add() {
    const newComment = {description: this.comment} as Comment;
    this.commentService.save(newComment, this.taskId).subscribe(commentId => {
      this.commentService.findById(commentId, this.taskId).subscribe(comment => this.comments.unshift(comment));
      this.comment = "";
      this.snackBar.open(`Comment saved!`, 'Close', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
    });
  }
}
