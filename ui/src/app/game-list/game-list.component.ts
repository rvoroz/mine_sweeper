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
  rows: number;
  columns: number;
  mines: number;
  games: any;

  constructor(private router: Router, private gameService: GameService){}

  ngOnInit(): void {
  }

  createGame() {
    if(!this.userId || !this.rows || !this.columns || !this.mines){
      alert("Please enter all fields ");
      return
    }
    const request = {
      userId: this.userId,
      gameConfig: {
        rows: this.rows,
        columns: this.columns,
        mines: this.mines
      }
    };
    this.gameService.createGame(request).subscribe(data => {
      if(data.error){
        alert(data.error);
        return;
      }
      this.router.navigate(["/game"], { queryParams: { gameId: data.id }})
    })
  }

  searchGames() {
    if(!this.userId){
      alert("Please enter your nickname first ");
      return
    }

    this.gameService.getGameList(this.userId).subscribe(data =>{
      this.games = data;
    });
  }

  continueGame(gameId: number){
    this.router.navigate(["/game"], { queryParams: { gameId: gameId }})
  }
}
