import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration-success-dialog',
  standalone: true,
  imports: [],
  templateUrl: './registration-success-dialog.component.html',
  styleUrls: ['./registration-success-dialog.component.css']
})
export class RegistrationSuccessDialogComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { username: string },
    public dialogRef: MatDialogRef<RegistrationSuccessDialogComponent>,
    private router: Router
  ) {
  }

  close(): void {
    this.dialogRef.close();
  }

  onNavigateToUserList() {
    this.router.navigate(['/userList']);
    this.close();
  }
}
