import { Component } from '@angular/core';
import {PassengerControllerService} from "../../core/api/v1";

@Component({
  selector: 'bs-not-authorized',
  templateUrl: './not-authorized.component.html',
  styleUrls: ['./not-authorized.component.scss']
})
export class NotAuthorizedComponent {
    constructor(private readonly acc: PassengerControllerService) {
    }

    click(): void {
      this.acc.getPasengerTransactions().subscribe({
        next: (v) => {
          console.log(v)
        },
        error: (err) => {
          console.log(err)
        }
      })
    }
}
