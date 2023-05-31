import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  // {
  //   path: "",
  //   pathMatch: "full",
  //   redirectTo: "/books",
  // },
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
