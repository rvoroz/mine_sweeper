import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  gameId: any;
  field: any;
  actionType: string = 'dig';
  status: string;

  constructor( private route: ActivatedRoute, private router: Router, private gameService: GameService) { }

  ngOnInit(): void {
    this.route
      .queryParams
      .subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.gameId = params['gameId'] || 0;
      });
    this.getGame();
  }

  getGame() {
    this.gameService.getGame(this.gameId).subscribe(data => {
      this.gameId = data.id;
      this.status = data.status;
      this.field = [];
      let row = []
      for (let i = 0; i < data.field.cells.length; i++) {
        row.push(data.field.cells[i]);
        if (row.length == data.field.columns) {
          this.field.push(row);
          row = [];
        }
      }
    });
  }

  actionCell(cellNum: number) {
    if(this.status == 'GAME_OVER' || this.status == 'COMPLETE' || this.status == 'PAUSED') return;
    const request = {
      gameId: this.gameId,
      cellNumber: cellNum
    };
    if (this.actionType == 'dig') {
      this.gameService.digCell(request).subscribe(data => {
        this.status = data.status;
        data.changedCells.forEach(cell => {
          if(cell.cellType == 'MINE'){
            alert('YOU ARE DEAD!');
          }
          this.field.forEach(row => {
            row.forEach(col => {
              if (col.cellNumber == cell.cellNumber) {
                col.open = cell.open;
              }
            });
          });
        });
        if(this.status == 'COMPLETED'){
          alert('YOU HAVE SURVIVED!');
        }
      });
    } else {
      this.gameService.flagCell(request).subscribe(data => {
        data.changedCells.forEach(cell => {
          this.field.forEach(row => {
            row.forEach(col => {
              if (col.cellNumber == cell.cellNumber) {
                col.flag = cell.flag;
              }
            });
          });
        });
      });
    }
  }

  pauseGame(){
    this.gameService.pauseGame(this.gameId).subscribe(data => {
      this.status = data.status;
    });
  }

  resumeGame(){
    this.gameService.resumeGame(this.gameId).subscribe(data => {
      this.status = data.status;
      this.field = [];
      let row = []
      for (let i = 0; i < data.field.cells.length; i++) {
        row.push(data.field.cells[i]);
        if (row.length == data.field.columns) {
          this.field.push(row);
          row = [];
        }
      }
    });
  }

  goBack(){
    this.router.navigate(["/game-list"]);
  }
}


