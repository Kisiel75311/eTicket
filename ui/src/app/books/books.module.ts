import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { BookListComponent } from "./components/book-list/book-list.component";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { BookReviewComponent } from "./components/book-review/book-review.component";
import { BookEditComponent } from "./components/book-edit/book-edit.component";
import { MatInputModule } from "@angular/material/input";
import { ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { ReviewComponent } from "./components/book-review/review/review.component";
import { NewReviewComponent } from "./components/book-review/new-review/new-review.component";
import { MatSliderModule } from "@angular/material/slider";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatRadioModule } from "@angular/material/radio";

@NgModule({
  declarations: [
    BookListComponent,
    BookReviewComponent,
    BookEditComponent,
    ReviewComponent,
    NewReviewComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatCardModule,
    MatSliderModule,
    MatFormFieldModule,
    MatRadioModule,
  ],
})
export class BooksModule {}
