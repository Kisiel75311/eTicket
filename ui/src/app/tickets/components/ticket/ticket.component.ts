import { Component, Input } from '@angular/core';
import {Ticket} from "../../../model/ticket";

@Component({
  selector: 'bs-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent {
    // @ts-ignore
  @Input() ticket: Ticket
}
