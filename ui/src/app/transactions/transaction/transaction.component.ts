import {Component, Input} from '@angular/core';
import {TransactionDto} from "../../core/api/v1";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'bs-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent {
  @Input() transaction: TransactionDto | undefined = undefined

  constructor(
    private readonly activatedRoute: ActivatedRoute
  ) {
    this.transaction = this.activatedRoute.snapshot.data["transactions"][0];
  }
}
