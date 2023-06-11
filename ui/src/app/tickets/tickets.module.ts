import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketComponent } from './ticket/ticket.component';
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import { TicketListComponent } from './ticket-list/ticket-list.component';
import {MatPaginatorModule} from "@angular/material/paginator";



@NgModule({
  declarations: [
    TicketComponent,
    TicketListComponent
  ],
  exports: [
    TicketComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatProgressBarModule,
    MatPaginatorModule
  ]
})
export class TicketsModule { }
