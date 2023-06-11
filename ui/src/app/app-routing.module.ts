import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {WelcomePageComponent} from "./layout/welcome-page/welcome-page.component";
import {ticketsResolver} from "./resolvers/ticket-list.resolver";
import {TicketListComponent} from "./tickets/ticket-list/ticket-list.component";
import {TransactionComponent} from "./transactions/transaction/transaction.component";
import {transactionsResolver} from "./resolvers/transaction-list.resolver";

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    component: WelcomePageComponent,
    resolve: {tickets: ticketsResolver}
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
    resolve: {transactions: transactionsResolver}
  }
  // {
  //   path: "books",
  //   component: BookListComponent,
  //   resolve: {
  //     books: BookListResolver,
  //   },
  // },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
