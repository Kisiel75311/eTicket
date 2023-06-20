import {Component} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from "@angular/material/sidenav";
import {NgIf} from "@angular/common";
import {MatListModule} from "@angular/material/list";
import {GlobalService} from "../../services/global.service";
import {AuthModule} from "../../auth/auth.module";
import {AuthControllerService} from "../../core/api/v1";
import {CustomSnackbarService} from "../../services/custom-snackbar.service";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatDialog} from "@angular/material/dialog";
import {LoginComponent} from "../../auth/login/login.component";
import {RegisterComponent} from "../../auth/register/register.component";
import {RouterLink, RouterOutlet} from "@angular/router";

@Component({
  selector: 'bs-main-page',
  templateUrl: 'main-page.component.html',
  styleUrls: ['main-page.component.scss'],
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatSidenavModule, NgIf, MatListModule, AuthModule, MatTooltipModule, RouterOutlet, RouterLink],
})
export class MainPageComponent {

  constructor(private readonly global: GlobalService, private readonly authService: AuthControllerService, private readonly snack: CustomSnackbarService, public dialog: MatDialog) {
  }

  isLoggedIn(): boolean {
    return this.global.isAccountSet();
  }

  isPassanger(): boolean {
    const acc = this.global.getAccount();
    return acc?.roles?.includes("ROLE_PASSENGER") ?? false
  }

  getBalance(): string | undefined {
    return this.global.getAccount()?.balance?.toFixed(2);
  }

  signOut(): void {
    this.authService.logoutUser().subscribe((_) => {
      this.global.setAccount(undefined);
      this.snack.open("Logged out");
    });
  }

  singin(): void {
    this.dialog.open(LoginComponent);
  }

  register(): void {
    this.dialog.open(RegisterComponent);
  }
}
