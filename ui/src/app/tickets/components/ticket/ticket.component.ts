import { Component, Input } from '@angular/core';
import {Ticket} from "../../model/ticket";

@Component({
  selector: 'bs-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent {
    @Input() ticket: Ticket = {
      id: 1,
      name: "lol",
      price: 12.3
    }
}
