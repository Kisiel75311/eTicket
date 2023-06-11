import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'bs-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent {
  constructor(private activatedRoute: ActivatedRoute){
    activatedRoute.data.subscribe((v) => console.log(v))
  }

}
