import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { BooksModule } from "./books/books.module";
import { RouterModule } from "@angular/router";
import { BookNoFoundComponent } from "./utils/components/book-no-found/book-no-found.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

@NgModule({
  declarations: [AppComponent, BookNoFoundComponent],
  imports: [
    BrowserModule,
    BooksModule,
    AppRoutingModule,
    RouterModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
