import { Component, Input } from '@angular/core';
import {TicketTypeDto} from "../../core/api/v1";
import {GlobalService} from "../../services/global.service";

@Component({
  selector: 'bs-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent {
  @Input() ticket: TicketTypeDto | undefined = undefined;

  constructor(private readonly global: GlobalService) {
  }

  get isLogged(): boolean {
    return this.global.getAccount() !== undefined;
  }
}
