import { Component, OnInit } from '@angular/core';
import { UserAccountDTO } from "../../model/UserAccountDTO";
import { UserService } from "../../services/user.service";
import {CommonModule} from "@angular/common";
import {MatPaginatorModule, PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule,MatPaginatorModule],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'
  ]
})
export class UserListComponent implements OnInit {
  public users: UserAccountDTO[] = [];
  public paginatedUsers: UserAccountDTO[] = [];
  public totalUsers: number = 0;
  public pageSize: number = 10;
  public currentPage: number = 0;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadPaginatedUsers();
  }

  loadPaginatedUsers() {
    this.userService.findAllUsers(this.currentPage, this.pageSize).subscribe(result => {
      if (result) {
        this.users = result.content;
        this.totalUsers = result.page.totalElements;
      }
    });
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadPaginatedUsers();
  }

  updatePaginatedUsers() {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedUsers = this.users.slice(startIndex, endIndex);
  }
}
