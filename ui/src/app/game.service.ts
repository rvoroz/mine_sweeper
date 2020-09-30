import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  gameUrl;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  constructor(
    private http: HttpClient) {
        this.gameUrl = environment.apiUrl;
     }

  createGame(request: any): Observable<any> {
    return this.http.post(`${this.gameUrl}/game/start`, request, this.httpOptions)
      .pipe();
  }

  getGame(gameId: number): Observable<any> {
    return this.http.get(`${this.gameUrl}/game/${gameId}`)
      .pipe();
  }

  getGameList(userId: string): Observable<any> {
    return this.http.get(`${this.gameUrl}/game/user/${userId}`)
      .pipe();
  }

  digCell(request): Observable<any> {
    return this.http.put(`${this.gameUrl}/game/dig`, request)
      .pipe();
  }

  flagCell(request): Observable<any> {
    return this.http.put(`${this.gameUrl}/game/flag`, request)
      .pipe();
  }

  pauseGame(gameId: number): Observable<any> {
    return this.http.put(`${this.gameUrl}/game/pause/${gameId}`, "")
      .pipe();
  }

  resumeGame(gameId: number): Observable<any> {
    return this.http.put(`${this.gameUrl}/game/resume/${gameId}`, "")
      .pipe();
  }
}
