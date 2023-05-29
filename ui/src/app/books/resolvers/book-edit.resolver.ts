import { Injectable } from "@angular/core";
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot,
} from "@angular/router";
import { Observable, of } from "rxjs";
import { Book } from "../model/book";
import { BooksService } from "../services/books.service";

@Injectable({
  providedIn: "root",
})
export class BookDetailsResolver implements Resolve<Book | undefined> {
  constructor(private bookService: BooksService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Book | undefined> {
    const bookId = route.paramMap.get("bookId");
    if (!bookId) {
      return of(undefined);
    }
    return this.bookService.findBookById(Number(bookId));
  }
}
