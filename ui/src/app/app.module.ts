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
import { SnackBarComponent } from './core/snack-bar/snack-bar.component';
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatButtonModule} from "@angular/material/button";
import { MainPageComponent } from './layout/main-page/main-page.component';
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {ApiModule, Configuration, ConfigurationParameters} from "./core/api/v1";
import { WelcomePageComponent } from './layout/welcome-page/welcome-page.component';
import {environment} from "../environments/environment";

export function apiConfigFactory(): Configuration {
  const params: ConfigurationParameters = {
    basePath: environment.basePath,
  };
  return new Configuration(params);
}


@NgModule({
  declarations: [AppComponent, SnackBarComponent, WelcomePageComponent],
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
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MainPageComponent,
    ApiModule.forRoot(apiConfigFactory)
  ],
  providers: [AuthService, GlobalService],
  bootstrap: [AppComponent],
})
export class AppModule {
}
