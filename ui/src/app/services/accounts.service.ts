import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Account} from "../model/account";

const accountsApiPrefix = "/api/accounts";

@Injectable({
  providedIn: "root",
})
export class BooksService {
  constructor(private readonly http: HttpClient) {
  }

  getAllAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(accountsApiPrefix);
  }


}
