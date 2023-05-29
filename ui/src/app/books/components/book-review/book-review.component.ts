import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Book } from "../../model/book";
import { Review } from "../../model/review";

@Component({
  selector: "bs-book-review",
  templateUrl: "./book-review.component.html",
  styleUrls: ["./book-review.component.scss"],
})
export class BookReviewComponent implements OnInit {
  book: Book;
  reviews: Review[];

  constructor(private activatedRoute: ActivatedRoute, private router: Router) {
    this.book = this.activatedRoute.snapshot.data["bookDetails"];
    this.reviews = this.activatedRoute.snapshot.data["reviews"];
  }

  ngOnInit(): void {}

  handleBackButton(): void {
    this.router.navigateByUrl("books");
  }

  handleCreateButton(): void {
    this.router.navigateByUrl("books/" + this.book.id + "/new-review");
  }
}
