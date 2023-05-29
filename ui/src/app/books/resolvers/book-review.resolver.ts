import { Injectable } from "@angular/core";
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot,
} from "@angular/router";
import { Observable, of } from "rxjs";
import { ReviewService } from "../services/review.service";
import { Review } from "../model/review";

@Injectable({
  providedIn: "root",
})
export class BookReviewResolver implements Resolve<Review[] | undefined> {
  constructor(private reviewService: ReviewService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Review[] | undefined> {
    const bookId = route.paramMap.get("bookId");
    if (!bookId) {
      return of(undefined);
    }
    return this.reviewService.getAllReviewsForBook(Number(bookId));
  }
}
