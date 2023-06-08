import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackBarComponent} from "../core/snack-bar/snack-bar.component";

@Injectable({providedIn: "root"})
export class CustomSnackbarService {
  constructor(private snackBar: MatSnackBar) {
  }

  public open(message: string, action = 'Close', duration = 4000): void {
    this.snackBar.openFromComponent(SnackBarComponent, {
      data: {message, action},
      duration: duration,
      verticalPosition: "bottom",
      horizontalPosition: "end"
    })
  }
}
