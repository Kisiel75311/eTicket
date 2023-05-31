import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketComponent } from './components/ticket/ticket.component';
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import { TicketListComponent } from './components/ticket-list/ticket-list.component';



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
    MatProgressBarModule
  ]
})
export class TicketsModule { }
