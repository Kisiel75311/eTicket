import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthControllerService, LoginRequest, UserInterface, UserInfoResponse} from "../../core/api/v1";
import {GlobalService} from "../../services/global.service";
import {CustomSnackbarService} from "../../services/custom-snackbar.service";
import {MatDialogRef} from "@angular/material/dialog";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'bs-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  hide: boolean = true
  isFormValid: boolean = false

  // @ts-ignore
  loginForm: FormGroup;

  constructor(private readonly authService: AuthControllerService,private readonly global: GlobalService,
              private readonly snack: CustomSnackbarService, private readonly dialogRef: MatDialogRef<LoginComponent>) {
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required]),
    });
    this.validateFormGroup();
    this.subscribeAllControls();
  }

  getCookie(name: string) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop()?.split(';').shift();
    else return "";
  }

  login() {
    let request: LoginRequest = {
      username: this.usernameControl.value,
      password: this.passwordControl.value
    };
    this.authService.authenticateUser(request).subscribe({
      next: (response: UserInfoResponse<UserInterface>) => {
          this.passwordControl.setValue("");
          this.global.setAccount(response);
          this.dialogRef.close();

          this.snack.open("Signed in!", "Close", 3000);

      },
      error: (error: any) => {
        let errorMessage = error.error.error || `Error Code: ${error.status}\nMessage: ${error.message}`;
        this.snack.open(errorMessage, "Close", 3000, "warn");
      }
    })
  }


  private subscribeAllControls(): void {
    Object.keys(this.loginForm.controls).forEach((key) => {
      this.loginForm.controls[key].valueChanges.subscribe((_) => {
        this.markControlsForKey(key);
        this.validateFormGroup();
      });
    });
  }

  private markControlsForKey(key: string): void {
    this.loginForm.controls[key].markAsTouched();
  }

  private isAllControlsValid(): boolean {
    return this.loginForm.valid;
  }

  private validateFormGroup(): void {
    if (this.loginForm.pristine || !this.isAllControlsValid()) {
      this.isFormValid = false;
      return;
    }
    this.isFormValid = true;
  }

  get passwordControl(): FormControl {
    return this.loginForm.get("password") as FormControl;
  }

  get isPasswordValid(): boolean {
    return this.passwordControl.hasError("required")
  }

  get usernameControl(): FormControl {
    return this.loginForm.get("username") as FormControl;
  }

  get isUsernameValid(): boolean {
    return this.usernameControl.hasError("required")
  }
}
