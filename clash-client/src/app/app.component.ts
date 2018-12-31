import { Component, OnInit, Input } from '@angular/core';
import { PlayerService } from './player/player.service';
import { PlayerDetailModel } from './player/PlayerDetailModel';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'clash-client';

  @Input() player: PlayerDetailModel;

  constructor(private playerService: PlayerService) {}

  ngOnInit(): void {
    // this.getPlayer();
  }

  getPlayer(): void {
    this.playerService.getPlayer()
      .subscribe(player => this.player = player);
  }
}
