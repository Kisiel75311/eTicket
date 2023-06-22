import { Component, Input } from '@angular/core';
import {TicketTypeDto} from "../../core/api/v1";
import {GlobalService} from "../../services/global.service";
import {MatDialog} from "@angular/material/dialog";
import {BuyTicketComponent} from "../buy-ticket/buy-ticket.component";

@Component({
  selector: 'bs-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent {
  @Input() ticket: TicketTypeDto | undefined = undefined;

  constructor(private readonly global: GlobalService, public dialog: MatDialog) {
  }

  buyTicket(): void {
    this.dialog.open(BuyTicketComponent, {
      data: this.ticket,
    });
  }
  get isLogged(): boolean {
    return this.global.getAccount() !== undefined;
  }
}
