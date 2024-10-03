import {ComponentFixture, TestBed} from '@angular/core/testing';
import {ReactiveFormsModule} from '@angular/forms';
import {RegistrationComponent} from './registration.component';
import {UserService} from "../../services/user.service";

describe('RegistrationComponent', () => {
  let component: RegistrationComponent;
  let fixture: ComponentFixture<RegistrationComponent>;
  let registrationService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    const serviceSpy = jasmine.createSpyObj('UserRegistrationService', ['registerUser']);

    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, RegistrationComponent],
      providers: [{provide: UserService, useValue: serviceSpy}]
    }).compileComponents();

    fixture = TestBed.createComponent(RegistrationComponent);
    component = fixture.componentInstance;
    registrationService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  type TestCase = [string, string, string, boolean];
  const testCases: TestCase[] = [
    // valid data
    ['testUser1', 'Valid123', 'Valid123', true],
    ['AlphaNumeric5', 'Password1', 'Password1', true],
    ['User01', 'StrongPassword1', 'StrongPassword1', true],
    ['username', 'ComplexPass1', 'ComplexPass1', true],
    ['testUser123', 'AnotherPass1', 'AnotherPass1', true],
    ['testUser222', 'spe?ialA1', 'spe?ialA1', true],

    // invalid username
    ['usr', 'Valid123', 'Valid123', false],
    ['', 'Valid123', 'Valid123', false],
    ['user@name', 'Valid123', 'Valid123', false],
    ['user name', 'Valid123', 'Valid123', false],
    ['?????', 'Valid123', 'Valid123', false],

    // invalid password
    ['validUser1', 'short', 'short', false],
    ['validUser2', 'NoNumber', 'NoNumber', false],
    ['validUser3', 'lowercase1', 'lowercase1', false],
    ['validUser4', 'UPPERCASE1', 'UPPERCASE1', false],

    // invalid username and password
    ['short', 'short', 'short', false],
    ['User', 'Password!', 'Password!', false],
  ];
  testCases.forEach(([username, password, repeatedPassword, expected]) => {
    it(`should validate form for username: ${username} and password: ${password} and repeatedPassword: ${repeatedPassword}`, () => {
      component.registerUserForm.setValue({username, password, repeatedPassword});
      expect(component.registerUserForm.valid).toBe(expected);
    });
  });

});
