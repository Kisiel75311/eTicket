import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {AuthControllerService, SignupRequest} from "../../core/api/v1";
import {CustomSnackbarService} from "../../services/custom-snackbar.service";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'bs-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  hide: boolean = true;
  isFormValid: boolean = false;


  // @ts-ignore
  registerGroup: FormGroup;

  constructor(private readonly authService: AuthControllerService, private readonly snack: CustomSnackbarService,
              private readonly dialogRef: MatDialogRef<RegisterComponent>){}

  ngOnInit(): void {
    this.registerGroup = new FormGroup({
      username: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required, Validators.minLength(8)]),
      email: new FormControl("", [Validators.required, Validators.email]),
      confirmpass: new FormControl("", [Validators.required])
    },
      {validators: passwordsMatch()});
    this.validateFormGroup();
    this.subscribeAllControls();
  }


  register() {
    if (this.registerGroup.valid) {
      const req: SignupRequest = {
        username: this.usernameControl.value,
        email: this.emailControl.value,
        password: this.passwordControl.value
      }
      this.authService.registerUser(req).subscribe({
        next: (_) => {
          this.snack.open("Registered", "Close", 3000, "accent")
          this.dialogRef.close();
        }
      })
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
    setTimeout(() => {
    if (this.registerGroup.pristine || !this.isAllControlsValid()) {
      this.isFormValid = false;
      return;
    }
    this.isFormValid = true;
    })
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

  get confirmControl(): FormControl {
    return this.registerGroup.get("confirmpass") as FormControl
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

  get doPasswordsDiffer(): boolean {
    const value = this.registerGroup.hasError("confirm");
    if (value) {
      this.confirmControl.setErrors({incorrect: true});
    } else {
      this.confirmControl.setErrors(null);
    }

    return value
  }

  get isPassLongEnough(): boolean {
    return this.passwordControl.hasError("minlength")
  }
}

function passwordsMatch(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const passw = control.get("password")?.value;
    const confirm = control.get("confirmpass")?.value;

    return passw === confirm ? null : {confirm: true}
  }
}
