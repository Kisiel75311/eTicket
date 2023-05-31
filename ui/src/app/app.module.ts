import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TicketsModule} from "./tickets/tickets.module";
import {AuthModule} from "./auth/auth.module";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {AuthService} from "./services/auth.service";
import {GlobalService} from "./services/global.service";
import {HttpClientModule} from '@angular/common/http';
import { SnackBarComponent } from './utils/snack-bar/snack-bar.component';
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatButtonModule} from "@angular/material/button";
import { MainPageComponent } from './layout/main-page/main-page.component';

@NgModule({
  declarations: [AppComponent, SnackBarComponent, MainPageComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    BrowserAnimationsModule,
    TicketsModule,
    AuthModule,
    MatSnackBarModule,
    HttpClientModule,
    MatProgressBarModule,
    MatButtonModule
  ],
  providers: [AuthService, GlobalService],
  bootstrap: [AppComponent],
})
export class AppModule {
}
