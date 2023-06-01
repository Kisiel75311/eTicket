import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Account} from "../model/account";
import {LoginDto, RegisterDto} from "../model/authDto";
import {GlobalService} from "./global.service";
import {CustomSnackbarService} from "./custom-snackbar.service";
import {Message} from "../model/message";

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
        this.snack.open("Signed in!" )
      },
      error: (error) => {
        this.snack.open("Couldn't sing in " + error.error.message)
      }
    });
  }

  signUp(registerDto: RegisterDto): void {
    this.http.post<Message>(accountsApiPrefix + "/signup", registerDto).subscribe({
      next: (_) => {
        this.snack.open("Signed Up!")
      },
      error: (error) => {
        this.snack.open("Couldn't sing up " + error.error.message)
      }
    })
  }

  singOut(): void {
    this.http.post<Message>(accountsApiPrefix + "/signup", undefined).subscribe({
      next: (_) => {
        this.snack.open("Signed Out!")
      },
      error: (error) => {
        this.snack.open("Couldn't sing out " + error.error.message)
      }
    })
  }

  addController(registerDto: RegisterDto): void {
    this.http.post<Message>(accountsApiPrefix + "/addController", registerDto).subscribe({
      next: (_) => {
        this.snack.open("Added controller!")
      },
      error: (error) => {
        this.snack.open(error.error.message)
      }
    })
  }

}
