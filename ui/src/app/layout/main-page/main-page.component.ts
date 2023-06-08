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

@Component({
  selector: 'bs-main-page',
  templateUrl: 'main-page.component.html',
  styleUrls: ['main-page.component.scss'],
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatSidenavModule, NgIf, MatListModule, AuthModule],
})
export class MainPageComponent {

  constructor(private readonly global: GlobalService, private readonly authService: AuthControllerService, private readonly snack: CustomSnackbarService) {
  }

  isLoggedIn(): boolean {
    return this.global.isAccountSet();
  }

  getBalance(): string | undefined {
    return this.global.getAccount()?.balance?.toFixed(2);
  }

  signOut(): void {
    this.authService.logoutUser();
    this.global.setAccount(undefined);
    this.snack.open("Logged out");
  }
}
