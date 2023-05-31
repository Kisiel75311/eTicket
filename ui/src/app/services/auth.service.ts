import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Account} from "../model/account";
import {LoginDto} from "../model/authDto";
import {GlobalService} from "./global.service";
import {CustomSnackbarService} from "./custom-snackbar.service";

const accountsApiPrefix = "/api/auth";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  constructor(private readonly http: HttpClient,
              private readonly global: GlobalService,
              private readonly snack: CustomSnackbarService) {
  }

  signIn(loginDto: LoginDto): void {
    this.http.post<Account>(accountsApiPrefix + "/signin", loginDto).subscribe({
      next: (data: Account) => {
        this.global.setAccount(data)
        this.snack.open("Sined in" )
      },
      error: (error) => {
        this.snack.open("Couldn't sing in" + error)
      }
    });
  }


}
