import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginDto} from "../../model/authDto";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'bs-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  hide: boolean = true
  isFormValid: boolean = false
  loginObject: LoginDto = {
    username: "",
    password: ""
  };

  // @ts-ignore
  loginForm: FormGroup;

  constructor(private readonly authService: AuthService) {
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(this.loginObject.username, [Validators.required]),
      password: new FormControl(this.loginObject.password, [Validators.required]),
    });
    this.validateFormGroup();
    this.subscribeAllControls();
  }


  login() {
    this.loginObject.username = this.usernameControl.value
    this.loginObject.password = this.passwordControl.value
    this.authService.signIn(this.loginObject)
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
