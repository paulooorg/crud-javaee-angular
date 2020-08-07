import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { TaskService } from '../task.service';
import { StatusService } from '../status.service';
import { Task } from '../task';
import { Status } from '../status';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {

  @Input() task: Task;

  statuses: Status[];

  constructor(private route: ActivatedRoute, private taskService: TaskService, private statusService: StatusService, 
    private location: Location, private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.getTask();
    this.getStatuses();
  }

  getTask(): void {
    this.task = {} as Task;
    const id = +this.route.snapshot.paramMap.get('id');
    if (id) {
      this.taskService.findById(id).subscribe(task => this.task = task);
    }
  }

  goBack(): void {
    this.location.back();
  }

  getStatuses(): void {
    this.statusService.getStatuses().subscribe(statuses => this.statuses = statuses);
  }

  save(): void {
    this.taskService.save(this.task).subscribe(task => {
      let id = this.task.id ?? +task;
      this.taskService.findById(id).subscribe(task => this.task = task);
      this.snackBar.open(`Task saved!`, 'Close', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
      this.router.navigate(['/tasks/edit', id]);
    });
  }
}
