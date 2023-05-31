import {Component} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from "@angular/material/sidenav";
import {NgIf} from "@angular/common";
import {MatListModule} from "@angular/material/list";

@Component({
  selector: 'bs-main-page',
  templateUrl: 'main-page.component.html',
  styleUrls: ['main-page.component.scss'],
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatSidenavModule, NgIf, MatListModule],
})
export class MainPageComponent {
}
