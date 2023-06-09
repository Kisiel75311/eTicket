import {Component, Inject} from "@angular/core";
import {MAT_SNACK_BAR_DATA, MatSnackBarRef} from "@angular/material/snack-bar";

@Component({
  selector: "bs-snack-bar",
  templateUrl: "./snack-bar.component.html",
  styleUrls: ["./snack-bar.component.scss"]
})
export class SnackBarComponent {
  progress = 100;
  private currentIntervalId: any;

  constructor(
    @Inject(MAT_SNACK_BAR_DATA) public data: any,
    private snackBarRef: MatSnackBarRef<SnackBarComponent>
  ) {
    this.snackBarRef.afterOpened().subscribe({
      next: (_) => {
        const duration = this.snackBarRef.containerInstance.snackBarConfig.duration;
        this.runProgressBar(duration ?? 0);
      }
    });
  }

  dismissWithAction() {
    this.cleanProgressBarInterval();
    this.snackBarRef.dismissWithAction();
  }

  runProgressBar(duration: number) {
    this.progress = 90;
    const step = 0.01;
    this.cleanProgressBarInterval();
    this.currentIntervalId = setInterval(() => {
      this.progress -= 100 * step;
      if (this.progress < 0) {
        this.cleanProgressBarInterval();
      }
    }, duration * step);
  }

  cleanProgressBarInterval() {
    clearInterval(this.currentIntervalId);
  }
}
