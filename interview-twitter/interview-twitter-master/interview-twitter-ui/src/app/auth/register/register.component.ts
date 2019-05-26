import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { User } from '../../models/user.model.';
import { UserService } from '../../services/user/user.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private model : User = {username : '', fullName : '', password : ''};
  private userSavedStatus : any = {fail : false, success : false};
  
  constructor(private userService : UserService, private cd : ChangeDetectorRef) { }

  ngOnInit() {
  }

  onSubmit(form : NgForm) {
    if (!form.valid)
      return;
    this.model = form.value;
    this.userService.create(this.model).subscribe(
      (user : User) => {
        this.handleUserSave(form, {fail: false, success: true});
      },
      () => {
        this.handleUserSave(form, {fail: true, success: false});
      }
    );
  }

  isFormSubmittedWithInvalidFullName(form: NgForm): boolean {
    const fullNameFormControl = form.form.controls['fullName'];
    return form.submitted && fullNameFormControl && !fullNameFormControl.valid;
  }

  isFormSubmittedWithInvalidUsername(form: NgForm): boolean {
    const usernameFormControl = form.form.controls['username'];
    return form.submitted && usernameFormControl && !usernameFormControl.valid;
  }

  isFormSubmittedWithInvalidPassword(form: NgForm): boolean {
    const passwordFormControl = form.form.controls['password'];
    return form.submitted && passwordFormControl && !passwordFormControl.valid;
  }

  private handleUserSave(form: NgForm, tweetSavedStatus: any) {
    this.userSavedStatus = tweetSavedStatus;
    form.resetForm();
    this.cd.detectChanges();
  }


}
