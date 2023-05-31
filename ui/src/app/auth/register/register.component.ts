import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RegisterDto} from "../../model/authDto";

@Component({
  selector: 'bs-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  hide: boolean = true
  isFormValid: boolean = false
  registerDto: RegisterDto = {
    username: "",
    email: "",
    password: ""
  };

  // @ts-ignore
  registerGroup: FormGroup;

  ngOnInit(): void {
    this.registerGroup = new FormGroup({
      username: new FormControl(this.registerDto.username, [Validators.required]),
      password: new FormControl(this.registerDto.password, [Validators.required]),
      email: new FormControl(this.registerDto.email, [Validators.required, Validators.email])
    });
    this.validateFormGroup();
    this.subscribeAllControls();
  }


  register() {
    if (this.registerGroup.valid) {
      console.log("Register")
    }
  }

  private subscribeAllControls(): void {
    Object.keys(this.registerGroup.controls).forEach((key) => {
      this.registerGroup.controls[key].valueChanges.subscribe((_) => {
        this.markControlsForKey(key);
        this.validateFormGroup();
      });
    });
  }

  private markControlsForKey(key: string): void {
    this.registerGroup.controls[key].markAsTouched();
  }

  private isAllControlsValid(): boolean {
    return this.registerGroup.valid;
  }

  private validateFormGroup(): void {
    if (this.registerGroup.pristine || !this.isAllControlsValid()) {
      this.isFormValid = false;
      return;
    }
    this.isFormValid = true;
  }

  get passwordControl(): FormControl {
    return this.registerGroup.get("password") as FormControl;
  }

  get isPasswordValid(): boolean {
    return this.passwordControl.hasError("required")
  }

  get usernameControl(): FormControl {
    return this.registerGroup.get("username") as FormControl;
  }

  get emailControl(): FormControl {
    return this.registerGroup.get("email") as FormControl;
  }

  get isUsernameValid(): boolean {
    return this.usernameControl.hasError("required")
  }

  get isEmailNotEntered(): boolean {
    return this.emailControl.hasError("required")
  }

  get isEmailValid(): boolean {
    return this.emailControl.hasError("email")
  }
}
