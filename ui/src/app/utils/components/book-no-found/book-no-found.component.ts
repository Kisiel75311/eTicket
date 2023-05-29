import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "bs-book-no-found",
  templateUrl: "./book-no-found.component.html",
  styleUrls: ["./book-no-found.component.scss"],
})
export class BookNoFoundComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}

  handleBackButton(): void {
    this.router.navigateByUrl("books");
  }
}
