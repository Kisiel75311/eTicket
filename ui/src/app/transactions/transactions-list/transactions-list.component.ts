import { Component } from '@angular/core';
import {TransactionDto} from "../../core/api/v1";
import {ActivatedRoute} from "@angular/router";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'bs-transactions-list',
  templateUrl: './transactions-list.component.html',
  styleUrls: ['./transactions-list.component.scss']
})
export class TransactionsListComponent {
  transactions: TransactionDto[];
  length = 50;
  pageSize = 10;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 25, 100];

  constructor(
    private readonly activatedRoute: ActivatedRoute
  ) {
    let trans = this.activatedRoute.snapshot.data["transactions"];
    this.transactions = trans.slice(0, this.pageSize);
    this.length = trans.length;
  }

  handlePageEvent(e: PageEvent) {
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    const index = e.pageIndex;
    this.transactions = this.activatedRoute.snapshot.data["tickets"].slice(index * this.pageSize, index * this.pageSize + this.pageSize);
  }
}
