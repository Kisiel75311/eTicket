import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Account} from "../model/account";
import {Ticket} from "../model/ticket";
import {Transaction} from "../model/transaction";

const accountsApiPrefix = "/api/accounts";

@Injectable({
  providedIn: 'root'
})
export class PassengerService {

  constructor(private readonly http: HttpClient) {
  }

  topUpAccount(amount: number): Observable<Account[]> {
    return this.http.post<Account[]>(accountsApiPrefix + "/toUpAccount/" + amount, "")
  }

  buyTicket(ticketTypeId: number, vehicleId: number): Observable<Ticket> {
    return this.http.post<Ticket>(accountsApiPrefix + "/buyTicket/" + ticketTypeId + "/" + vehicleId, "")
  }

  getTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(accountsApiPrefix + "/transactions")
  }

  getOneTransaction(id: number): Observable<Transaction> {
    return this.http.get<Transaction>(accountsApiPrefix + "/transactions/" + id)
  }

  getTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(accountsApiPrefix + "/tickets")
  }

}
