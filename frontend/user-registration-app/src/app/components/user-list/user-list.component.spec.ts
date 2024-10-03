import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserListComponent } from './user-list.component';
import { UserService } from '../../services/user.service';
import { of } from 'rxjs';
import {NoopAnimationsModule} from "@angular/platform-browser/animations";

describe('UserListComponent', () => {
  let component: UserListComponent;
  let fixture: ComponentFixture<UserListComponent>;
  let userServiceMock: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    userServiceMock = jasmine.createSpyObj('UserService', ['findAllUsers']);

    await TestBed.configureTestingModule({
      imports: [UserListComponent,NoopAnimationsModule],
      providers: [
        { provide: UserService, useValue: userServiceMock },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;

    userServiceMock.findAllUsers.and.returnValue(of({
      content: [
        { id: 1, username: 'test1', registerDate: new Date(), lastLoginDate: new Date() },
        { id: 2, username: 'test2', registerDate: new Date(), lastLoginDate: new Date() },
      ],
      page: { size: 10, number: 0, totalElements: 15, totalPages: 2 }
    }));

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch users on init', () => {
    component.ngOnInit();
    expect(userServiceMock.findAllUsers).toHaveBeenCalled();
    expect(component.users.length).toBe(2);
  });

});
