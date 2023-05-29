import { Component, Input, OnInit } from "@angular/core";
import { Book } from "src/app/books/model/book";
import { Review } from "src/app/books/model/review";

@Component({
  selector: "bs-review",
  templateUrl: "./review.component.html",
  styleUrls: ["./review.component.scss"],
})
export class ReviewComponent implements OnInit {
  @Input()
  review: Review;

  @Input()
  book: Book;

  constructor() {}

  ngOnInit(): void {}
}
