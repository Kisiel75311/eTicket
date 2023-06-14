import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {WelcomePageComponent} from "./layout/welcome-page/welcome-page.component";
import {ticketsResolver} from "./resolvers/ticket-list.resolver";
import {TicketListComponent} from "./tickets/ticket-list/ticket-list.component";
import {TransactionComponent} from "./transactions/transaction/transaction.component";
import {transactionsResolver} from "./resolvers/transaction-list.resolver";
import {transactionsGuard} from "./auth/guards/transactions.guard";
import {NotAuthorizedComponent} from "./layout/not-authorized/not-authorized.component";

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
    component: TransactionComponent,
    resolve: {transactions: transactionsResolver},
    canActivate: [transactionsGuard]
  },
  {
    path: "notauthorized",
    pathMatch: "full",
    component: NotAuthorizedComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
