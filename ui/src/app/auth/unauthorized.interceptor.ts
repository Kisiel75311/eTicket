import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from "rxjs/operators"
import {GlobalService} from "../services/global.service";
import {CustomSnackbarService} from "../services/custom-snackbar.service";
import {AccountControllerService} from "../core/api/v1";

@Injectable()
export class UnauthorizedInterceptor implements HttpInterceptor {

  constructor(private readonly global: GlobalService, private readonly snack: CustomSnackbarService, private readonly account: AccountControllerService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(catchError((error: HttpErrorResponse) => {
      const acc = this.global.getAccount()

      if (acc === undefined && error.status == 401) {
        this.account.getAccountById().subscribe({
          next: (v) => {
            this.global.setAccount(v)
          },
          error: (_) => {
            this.snack.open("You need to be signed in to perform this action", "Close", 6000, "warn");
          }
        })
      }
      return throwError(() => new Error(error.message));
    }));
  }
}
