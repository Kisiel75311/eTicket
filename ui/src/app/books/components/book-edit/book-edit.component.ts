import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {Book} from "../../model/book";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BooksService} from "../../services/books.service";

@Component({
  selector: "bs-book-edit",
  templateUrl: "./book-edit.component.html",
  styleUrls: ["./book-edit.component.scss"],
})
export class BookEditComponent implements OnInit {
  book: Book;

  formGroup: FormGroup;

  isFormValid: boolean = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private bookService: BooksService
  ) {
    this.book = this.activatedRoute.snapshot.data["edit"];
  }

  ngOnInit(): void {
    this.initFormGroup();
    this.validateFormGroup();
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

  private markControlsForKey(key: string): void {
    this.formGroup.controls[key].markAsTouched();
  }

  private isAllControlsValid(): boolean {
    return (
      this.titleControl.valid &&
      this.authorControl.valid &&
      this.yearControl.valid &&
      this.descriptionControl.valid
    );
  }

  private initFormGroup(): void {
    this.formGroup = new FormGroup({
      title: new FormControl(this.book.title, [
        Validators.required,
        Validators.maxLength(50),
      ]),
      author: new FormControl(this.book.author, [
        Validators.required,
        Validators.maxLength(50),
      ]),
      year: new FormControl(this.book.year, [
        Validators.min(1000),
        Validators.max(2023),
      ]),
      description: new FormControl(this.book.description, [
        Validators.maxLength(1000),
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

  handleCancelButton(): void {
    this.router.navigateByUrl("books");
  }

  handleSaveButton(): void {
    const editedBook = {
      id: this.book.id,
      title: this.titleControl.value,
      author: this.authorControl.value,
      year: this.yearControl.value,
      description: this.descriptionControl.value,
    };
    this.bookService.saveBook(editedBook);
    this.router.navigateByUrl("books");
  }

  get titleControl(): FormControl {
    return this.formGroup.get("title") as FormControl;
  }

  get authorControl(): FormControl {
    return this.formGroup.get("author") as FormControl;
  }

  get yearControl(): FormControl {
    return this.formGroup.get("year") as FormControl;
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

  get hasDescriptionIncorrectLength(): boolean {
    return this.descriptionControl.hasError("maxlength");
  }

  get isYearInInvalidIntervalTime(): boolean {
    return this.yearControl.hasError("min") || this.yearControl.hasError("max");
  }
}
