import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from "./game/game.component"
import { GameListComponent } from "./game-list/game-list.component"

const routes: Routes = [
  { path: '', redirectTo: "/game-list", pathMatch: "full" },
  { path: 'game-list', component: GameListComponent },
  { path: 'game', component: GameComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
