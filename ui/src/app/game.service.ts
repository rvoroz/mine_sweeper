import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private gameUrl = 'http://ec2-54-187-86-211.us-west-2.compute.amazonaws.com:8080';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  constructor(
    private http: HttpClient) { }

  createGame(request: any): Observable<any> {
    return this.http.post(`${this.gameUrl}/game/start`, request, this.httpOptions)
      .pipe();
  }

  getGame(gameId: number): Observable<any> {
    return this.http.get(`${this.gameUrl}/game/${gameId}`)
      .pipe();
  }

  getGameList(userId: number): Observable<any> {
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
