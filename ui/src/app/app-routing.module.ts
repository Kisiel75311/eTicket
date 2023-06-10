import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {WelcomePageComponent} from "./layout/welcome-page/welcome-page.component";
import {ticketsResolver} from "./resolvers/ticket-list.resolver";

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    component: WelcomePageComponent,
    resolve: {tickets: ticketsResolver}
  },
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
