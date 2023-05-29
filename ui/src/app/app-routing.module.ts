import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { BookListComponent } from "./books/components/book-list/book-list.component";
import { BookListResolver } from "./books/resolvers/book-list.resolver";
import { BookReviewComponent } from "./books/components/book-review/book-review.component";
import { BookReviewResolver } from "./books/resolvers/book-review.resolver";
import { BookEditComponent } from "./books/components/book-edit/book-edit.component";
import { BookDetailsResolver } from "./books/resolvers/book-edit.resolver";
import { BookNoFoundComponent } from "./utils/components/book-no-found/book-no-found.component";
import { NewReviewComponent } from "./books/components/book-review/new-review/new-review.component";

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    redirectTo: "/books",
  },
  {
    path: "books",
    component: BookListComponent,
    resolve: {
      books: BookListResolver,
    },
  },
  {
    path: "books/:bookId/reviews",
    component: BookReviewComponent,
    resolve: {
      bookDetails: BookDetailsResolver,
      reviews: BookReviewResolver,
    },
  },
  {
    path: "books/:bookId/edit",
    component: BookEditComponent,
    resolve: {
      edit: BookDetailsResolver,
    },
  },
  {
    path: "books/:bookId/new-review",
    component: NewReviewComponent,
  },
  {
    path: "**",
    pathMatch: "full",
    component: BookNoFoundComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
