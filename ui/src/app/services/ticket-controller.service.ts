import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../model/ticket";

const accountsApiPrefix = "/api/ticket-controller";

@Injectable({
  providedIn: "root",
})
export class TicketControllerService {
  constructor(private readonly http: HttpClient) {
  }

  validate(ticketCode: number, vehicleId: number): Observable<Ticket[]> {
    let queryParams = new HttpParams();
    queryParams.append("ticketCode", ticketCode)
    queryParams.append("vehicleId", vehicleId)
    return this.http.get<Ticket[]>(accountsApiPrefix + "/validate-ticket", {params: queryParams});
  }

}
