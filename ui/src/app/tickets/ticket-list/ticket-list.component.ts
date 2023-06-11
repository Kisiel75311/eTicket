import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TicketTypeDto} from "../../core/api/v1";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'bs-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.scss']
})
export class TicketListComponent {
  tickets: TicketTypeDto[];
  length = 50;
  pageSize = 10;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 25, 100];

  constructor(
    private readonly activatedRoute: ActivatedRoute
  ) {
    let tickets = this.activatedRoute.snapshot.data["tickets"];
    this.tickets = tickets.slice(0, this.pageSize);
    this.length = tickets.length;
  }

  handlePageEvent(e: PageEvent) {
      this.pageSize = e.pageSize;
      this.pageIndex = e.pageIndex;
      const index = e.pageIndex;
      this.tickets = this.activatedRoute.snapshot.data["tickets"].slice(index * this.pageSize, index * this.pageSize + this.pageSize);
  }
}
