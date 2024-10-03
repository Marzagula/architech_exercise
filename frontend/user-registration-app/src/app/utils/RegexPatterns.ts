import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class RegexPatterns {
  static readonly USERNAME_PATTERN = /^[a-zA-Z0-9]{5,}$/;
  /*
    (?=.*[a-z]) is at least one small letter
    (?=.*[A-Z]) is at least one big letter
    (?=.*\d) is at least one digit
    [A-Za-z\d\S]{8,} as at least 8 characters long and only letters,digits and special characters are used
  * */
  static readonly PASSWORD_PATTERN = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d\S]{8,}$/;
}
