import {Injectable} from '@angular/core';
import {catchError, map, Observable, throwError} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {UserRegistrationDTO} from "../model/UserRegistrationDTO";
import {UserAccountDTO} from "../model/UserAccountDTO";
import {PageableResponse} from "../model/PageableResponse";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userRegistrationUrl: string = 'http://localhost:8080/api/v1/users/register';
  findAllUsersUrl: string = 'http://localhost:8080/api/v1/users';

  constructor(private http: HttpClient) {
  }

  registerUser(userRegistration: UserRegistrationDTO): Observable<UserAccountDTO> {
    return this.http.post<UserAccountDTO>(this.userRegistrationUrl, userRegistration).pipe(
      map((response: UserAccountDTO) => response),
      catchError((error) => {
        return throwError(() => new Error(error.error.errorMessage));
      })
    );
  }

  findAllUsers(page: number, size: number): Observable<PageableResponse<UserAccountDTO>> {
    return this.http.get<PageableResponse<UserAccountDTO>>(`${this.findAllUsersUrl}?page=${page}&size=${size}`).pipe(
      map((response) => response),
      catchError((error) => {
        return throwError(() => new Error(error.error.errorMessage));
      })
    );
  }
}
