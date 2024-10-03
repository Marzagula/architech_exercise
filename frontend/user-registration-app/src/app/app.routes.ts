import {Routes} from '@angular/router';
import {RegistrationComponent} from "./components/registration/registration.component";
import {HomepageComponent} from "./components/homepage/homepage.component";
import {UserListComponent} from "./components/user-list/user-list.component";

export const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'registerUser', component: RegistrationComponent},
  {path: 'userList', component: UserListComponent}
];
