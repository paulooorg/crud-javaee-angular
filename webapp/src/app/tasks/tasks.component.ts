import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { Task } from '../task';
import { TaskService } from '../task.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements AfterViewInit {
  displayedColumns: string[] = ['id', 'creationDate', 'status', 'updateDate', 'title', 'description', 'options'];

  dataSource: MatTableDataSource<Task>;

  resultsLength = 0;

  searchText;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private taskService: TaskService, private snackBar: MatSnackBar) { }

  ngAfterViewInit(): void {
    this.getTasks(1, 10);
  }

  getServerData(event: PageEvent) {
    console.log(event);
    if (this.searchText) {
      console.log('search');
      this.search(event.pageIndex + 1, event.pageSize);
    } else {
      console.log('get tasks');
      this.getTasks(event.pageIndex + 1, event.pageSize);
    }
  }

  getTasks(page, pageSize): void {
    this.taskService.getTasks(page, pageSize).subscribe(tasks => {
      this.dataSource = new MatTableDataSource<Task>(tasks.content);
      this.resultsLength = tasks.totalCount;
    });
  }

  search(page, pageSize): void {
    this.taskService.search(this.searchText, page, pageSize).subscribe(tasks => {
      this.dataSource = new MatTableDataSource<Task>(tasks.content);
      this.resultsLength = tasks.totalCount;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.searchText = filterValue;
    if (this.searchText) {
      this.search(1, 10);
    } else {
      this.getTasks(1, 10);
    }
  }

  delete(task: Task) {
    this.taskService.delete(task).subscribe(() => {
      this.snackBar.open(`Task deleted!`, 'Close', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
      this.getTasks(1, 10);
    });
  }
}