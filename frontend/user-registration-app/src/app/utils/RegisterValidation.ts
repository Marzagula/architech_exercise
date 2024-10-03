import {Injectable} from '@angular/core';
import {RegexPatterns} from "./RegexPatterns";

@Injectable({
  providedIn: 'root'
})

export class RegisterValidation {
  isUsernameValid(username: string): boolean {
    const usernamePattern = RegexPatterns.USERNAME_PATTERN;
    return usernamePattern.test(username);
  }


  isPasswordValid(password: string): boolean {
    const passwordPattern = RegexPatterns.PASSWORD_PATTERN;
    return passwordPattern.test(password);
  }

}
