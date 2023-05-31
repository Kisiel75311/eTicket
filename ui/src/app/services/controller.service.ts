import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AccountDto} from "../model/account";
import {Observable} from "rxjs";

const accountsApiPrefix = "/api/accounts";

@Injectable({
  providedIn: 'root'
})
export class PassengerService {

  constructor(private readonly http: HttpClient) {
  }

  createAccount(account: AccountDto): Observable<AccountDto> {
    return this.http.post<AccountDto>(accountsApiPrefix + "/", account)
  }

  getAccount(id: number): Observable<AccountDto> {
    return this.http.get<AccountDto>(accountsApiPrefix + "/" + id)
  }

  getAllAccounts(): Observable<AccountDto[]> {
    return this.http.get<AccountDto[]>(accountsApiPrefix + "/all")
  }
}
