import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router"
import { GameService } from "../game.service";

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {

  userId: string;
  game: any;

  constructor(private router: Router, private gameService: GameService){}

  ngOnInit(): void {
  }

  startGame() {
    this.router.navigate(["/game"])
  }

  
}
