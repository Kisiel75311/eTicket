import {Component} from '@angular/core';
import {Ticket} from "../../model/ticket";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'bs-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.scss']
})
export class TicketListComponent {
  tickets: Ticket[];

  constructor(
    private readonly activatedRoute: ActivatedRoute
  ) {
    this.tickets = this.activatedRoute.snapshot.data["tickets"];
  }
}
