import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../model/ticket";

const accountsApiPrefix = "/api/tickets";

@Injectable({
  providedIn: "root",
})
export class TicketService {
  constructor(private readonly http: HttpClient) {
  }

  getAllTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(accountsApiPrefix);
  }


}
