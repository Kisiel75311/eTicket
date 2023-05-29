import {Component} from "@angular/core";
import {Book} from "../../model/book";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: "bs-book-list",
  templateUrl: "./book-list.component.html",
  styleUrls: ["./book-list.component.scss"],
})
export class BookListComponent {
  initialBooks: Book[];

  constructor(
    private readonly activatedRoute: ActivatedRoute
  ) {
    this.initialBooks = this.activatedRoute.snapshot.data["books"];
  }
}
