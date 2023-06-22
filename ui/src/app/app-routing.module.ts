import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {WelcomePageComponent} from "./layout/welcome-page/welcome-page.component";
import {ticketsResolver} from "./resolvers/ticket-list.resolver";
import {TicketListComponent} from "./tickets/ticket-list/ticket-list.component";
import {transactionsResolver} from "./resolvers/transaction-list.resolver";
import {transactionsGuard} from "./auth/guards/transactions.guard";
import {NotAuthorizedComponent} from "./layout/not-authorized/not-authorized.component";
import {TransactionsListComponent} from "./transactions/transactions-list/transactions-list.component";
import {CheckTicketComponent} from "./layout/check-ticket/check-ticket.component";
import {AddControllerComponent} from "./layout/add-controller/add-controller.component";

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    component: WelcomePageComponent
  },
  {
    path: "tickets",
    pathMatch: "full",
    component: TicketListComponent,
    resolve: {tickets: ticketsResolver}
  },
  {
    path: "transactions",
    pathMatch: "full",
    component: TransactionsListComponent,
    resolve: {transactions: transactionsResolver},
    canActivate: [transactionsGuard]
  },
  {
    path: "notauthorized",
    pathMatch: "full",
    component: NotAuthorizedComponent
  },
  {
    path: "check-ticket",
    pathMatch: "full",
    component: CheckTicketComponent
  },
  {
    path: "add-controller",
    pathMatch: "full",
    component: AddControllerComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
