import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AccountControllerService, PassengerControllerService, TicketTypeDto} from "../../core/api/v1";
import {CustomSnackbarService} from "../../services/custom-snackbar.service";
import {FormControl, Validators} from "@angular/forms";
import {GlobalService} from "../../services/global.service";

@Component({
  selector: 'bs-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.scss']
})
export class BuyTicketComponent {
  controler: FormControl<number | null>;
  constructor(
    public dialogRef: MatDialogRef<BuyTicketComponent>,
    @Inject(MAT_DIALOG_DATA) public data: TicketTypeDto,
    private readonly pass: PassengerControllerService,
    private readonly snack: CustomSnackbarService,
    private readonly global: GlobalService,
    private readonly account: AccountControllerService
  ) {
    this.controler = new FormControl(null, [Validators.required])
  }

  num: number | undefined

  onNoClick(): void {
    this.dialogRef.close();
  }

  buyTicket(): void {
    console.log(this.controler.valid)
    this.pass.buyTicket(this.data.id ?? -1, this.num ?? -1).subscribe({
      next: (_) => {
        this.snack.open("Successfully bought ticket")
        this.account.getAccountById().subscribe({
          next: (v) => {
            this.global.setAccount(v)
          }
        })
    },
      error: (_) => {
        this.snack.open("Couldn't buy ticket", "Close", 4000, "warn")
      }
    })
    this.dialogRef.close();
  }

  protected readonly encodeURI = encodeURI;
}
