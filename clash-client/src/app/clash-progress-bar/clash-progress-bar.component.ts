import { Component, OnInit } from '@angular/core';
import { LoadingBarService } from '@ngx-loading-bar/core';

@Component({
  selector: 'app-clash-progress-bar',
  templateUrl: './clash-progress-bar.component.html',
  styleUrls: ['./clash-progress-bar.component.scss']
})
export class ClashProgressBarComponent implements OnInit {

  constructor(public loader: LoadingBarService) {}

  ngOnInit() {
  }

}

