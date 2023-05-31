import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot,} from "@angular/router";
import {Observable} from "rxjs";
import {TicketService} from "../services/tickets.service";
import {Ticket} from "../model/ticket";

@Injectable({
  providedIn: "root",
})
export class BookListResolver implements Resolve<Ticket[]> {
  constructor(private booksService: TicketService) {
  }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Ticket[]> {
    return this.booksService.getAllTickets();
  }
}
