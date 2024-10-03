import {Component} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidatorFn, Validators} from "@angular/forms";
import {RegisterValidation} from "../../utils/RegisterValidation";
import {UserRegistrationDTO} from "../../model/UserRegistrationDTO";
import {UserService} from "../../services/user.service";
import {catchError, Observable, of} from "rxjs";
import {CommonModule} from "@angular/common";
import {UserAccountDTO} from "../../model/UserAccountDTO";
import {RegexPatterns} from "../../utils/RegexPatterns";
import {RegistrationSuccessDialogComponent} from "../registration-success-dialog/registration-success-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css', '../../../styles.css']
})
export class RegistrationComponent {
  registerUserForm: FormGroup;
  registrationResult$!: Observable<UserAccountDTO | null>;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    public validation: RegisterValidation,
    public registrationService: UserService,
    private dialog: MatDialog
  ) {
    this.registerUserForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(5), Validators.pattern(RegexPatterns.USERNAME_PATTERN)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.pattern(RegexPatterns.PASSWORD_PATTERN)]],
      repeatedPassword: ['', [Validators.required, Validators.minLength(8)]]
    }, {
      validators: this.passwordMatchValidator()
    });
  }

  get username() {
    return this.registerUserForm.get('username');
  }

  get password() {
    return this.registerUserForm.get('password');
  }

  get repeatedPassword() {
    return this.registerUserForm.get('repeatedPassword');
  }

  onSubmit() {
    if (this.registerUserForm.valid) {
      const userRegistration = new UserRegistrationDTO(
        this.username?.value,
        this.password?.value
      );
      this.registerUser(userRegistration);
    }
  }

  registerUser(user: UserRegistrationDTO) {
    this.errorMessage = '';
    this.registrationResult$ = this.registrationService.registerUser(user).pipe(
      catchError((error) => {
        this.errorMessage = error.message;
        return of(null);
      })
    );

    this.registrationResult$.subscribe(result => {
      if (result) {
        this.dialog.open(RegistrationSuccessDialogComponent, {
          data: {username: result.username},
        });
      }
    });
  }

  passwordMatchValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      const password = control.get('password')?.value;
      const repeatPassword = control.get('repeatedPassword')?.value;
      return password === repeatPassword ? null : {passwordMismatch: true};
    };
  }
}
