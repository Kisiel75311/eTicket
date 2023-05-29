import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ReviewService} from "src/app/books/services/review.service";

enum Rates {
  "Very bad" = 1,
  "Bad" = 2,
  "I don't know" = 3,
  "Good" = 4,
  "Very good" = 5,
}

@Component({
  selector: "bs-new-review",
  templateUrl: "./new-review.component.html",
  styleUrls: ["./new-review.component.scss"],
})
export class NewReviewComponent implements OnInit {
  bookId: string | null;

  formGroup: FormGroup;

  isFormValid: boolean = false;

  readonly rates = Rates;

  get arrayRates(): string[] {
    const array = Object.keys(this.rates);
    return array.slice(array.length / 2);
  }

  get titleControl(): FormControl {
    return this.formGroup.get("title") as FormControl;
  }

  get authorControl(): FormControl {
    return this.formGroup.get("author") as FormControl;
  }

  get rateControl(): FormControl {
    return this.formGroup.get("rate") as FormControl;
  }

  get descriptionControl(): FormControl {
    return this.formGroup.get("description") as FormControl;
  }

  get isTitleNotEntered(): boolean {
    return this.titleControl.hasError("required");
  }

  get hasTitleIncorrectLength(): boolean {
    return this.titleControl.hasError("maxlength");
  }

  get isAuthorNotEntered(): boolean {
    return this.authorControl.hasError("required");
  }

  get hasAuthorIncorrectLength(): boolean {
    return this.authorControl.hasError("maxlength");
  }

  get isDescriptionNotEntered(): boolean {
    return this.descriptionControl.hasError("required");
  }

  get hasDescriptionIncorrectLegth(): boolean {
    return this.descriptionControl.hasError("maxlength");
  }

  get isRateChosen(): boolean {
    return this.rateControl.hasError("required");
  }

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private reviewService: ReviewService
  ) {}

  ngOnInit(): void {
    this.initFormGroup();
    this.validateFormGroup();
    this.getBookIdFromUrl();
    this.subscribeAllControls();
  }

  private subscribeAllControls(): void {
    Object.keys(this.formGroup.controls).forEach((key) => {
      this.formGroup.controls[key].valueChanges.subscribe((_) => {
        this.markControlsForKey(key);
        this.validateFormGroup();
      });
    });
  }

  private getBookIdFromUrl(): void {
    this.bookId = this.route.snapshot.paramMap.get("bookId");
  }

  private initFormGroup(): void {
    this.formGroup = new FormGroup({
      title: new FormControl("", [
        Validators.required,
        Validators.maxLength(50),
      ]),
      author: new FormControl("", [
        Validators.required,
        Validators.maxLength(50),
      ]),
      rate: new FormControl("", [Validators.required]),
      description: new FormControl("", [
        Validators.required,
        Validators.maxLength(50),
      ]),
    });
  }

  private validateFormGroup(): void {
    if (this.formGroup.pristine || !this.isAllControlsValid()) {
      this.isFormValid = false;
      return;
    }
    this.isFormValid = true;
  }

  private markControlsForKey(key: string): void {
    this.formGroup.controls[key].markAsTouched();
  }

  private isAllControlsValid(): boolean {
    return (
      this.titleControl.valid &&
      this.authorControl.valid &&
      this.rateControl.valid &&
      this.descriptionControl.valid
    );
  }

  private enumValue(key: string): string {
    const index = Object.keys(this.rates).indexOf(key);
    const value = Object.values(this.rates)[index];
    return value.toString();
  }

  handleCancelButton(): void {
    this.router.navigateByUrl("books");
  }

  handleSaveButton(): void {
    if (!this.bookId) {
      this.isFormValid = false;
      return;
    }

    this.reviewService.getAllReviews().subscribe((reviews) => {
      const lastReview = reviews.reduce((acc, curr) =>
        acc.id > curr.id ? acc : curr
      );

      const rateNumber = this.enumValue(this.rateControl.value);

      const editedBook = {
        id: lastReview.id + 1,
        title: this.titleControl.value,
        author: this.authorControl.value,
        rate: Number(rateNumber),
        description: this.descriptionControl.value,
        forBook: Number(this.bookId),
      };
      this.reviewService.saveNewReview(editedBook);
      this.router.navigateByUrl("books/" + this.bookId + "/reviews");
    });
  }
}
