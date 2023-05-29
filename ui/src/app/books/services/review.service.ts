import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { Review } from "../model/review";

const reviewsApiPrefix = "/api/reviews";

@Injectable({
  providedIn: "root",
})
export class ReviewService {
  constructor(private readonly http: HttpClient) {}

  getAllReviewsForBook(bookId: number): Observable<Review[]> {
    return this.http.get<Review[]>(reviewsApiPrefix + "?forBook=" + bookId);
  }

  saveNewReview(review: Review): void {
    this.http.post<Review>(reviewsApiPrefix, review).subscribe({
      next: (data) => {
        console.log(data);
      },
      error: (error) => {
        console.error("There was an error!", error);
      },
    });
  }

  getAllReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(reviewsApiPrefix);
  }
}
