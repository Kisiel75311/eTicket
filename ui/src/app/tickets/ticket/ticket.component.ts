import { Component, Input } from '@angular/core';
import {TicketTypeDto} from "../../core/api/v1";

@Component({
  selector: 'bs-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent {
  @Input() ticket: TicketTypeDto | undefined = undefined;
}
