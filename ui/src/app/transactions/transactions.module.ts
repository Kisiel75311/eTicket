import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionComponent } from './transaction/transaction.component';
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatDividerModule} from "@angular/material/divider";
import { TransactionsListComponent } from './transactions-list/transactions-list.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {TicketsModule} from "../tickets/tickets.module";
import {MatIconModule} from "@angular/material/icon";



@NgModule({
  declarations: [
    TransactionComponent,
    TransactionsListComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    MatPaginatorModule,
    TicketsModule,
    MatIconModule
  ]
})
export class TransactionsModule { }
