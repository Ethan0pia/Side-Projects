import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  buttonClicked = false;
  clicks = 0;
  arrClicks = [];

  buttonWasClicked() {
    this.buttonClicked = true;
    this.clicks++;
    this.arrClicks.push(this.clicks);
  }

}
