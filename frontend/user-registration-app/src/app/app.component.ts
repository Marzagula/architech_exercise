import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {RegistrationComponent} from "./components/registration/registration.component";
import {NavbarComponent} from "./components/navbar/navbar.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RegistrationComponent, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'user-registration-app';
}
