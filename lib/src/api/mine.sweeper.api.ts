import ActionResponse from "./core/action.response";
import EventResponse from "./core/event.response";
import GameRequest from "./core/game.request";
import GameResponse from "./core/game.response";
import * as fetch from "node-fetch"

interface IMineSweeperClient {
    startGame(request: GameRequest): Promise<GameResponse>
    pauseGame(gameId: string): Promise<EventResponse>
    resumeGame(gameId: string): Promise<GameResponse>
    flagCell(gameId: string, cellNumber: number): Promise<ActionResponse>
    digCell(gameId: string, cellNumber: number): Promise<ActionResponse>
    endGame(gameId: string): Promise<EventResponse>
}

class MineSweeperClient {
    private host: string;

    constructor(host: string){
        this.host = host;
    }

    public async startGame(request: GameRequest): Promise<GameResponse> {
        return JSON.parse(await this.fetchData('game/start', 'post', request, {'Content-Type': 'application/json'}));
    }

    public async pauseGame(gameId: string): Promise<EventResponse> {
        return JSON.parse(await this.fetchData(`game/pause/${gameId}`, 'put', null, {}));
    }

    public async resumeGame(gameId: string): Promise<GameResponse> {
        return JSON.parse(await this.fetchData(`game/resume/${gameId}`, 'put', null, {}));
    }
    
    public async flagCell(gameId: string, cellNumber: number): Promise<ActionResponse> {
        return JSON.parse(await this.fetchData('game/flag', 'put', {gameId, cellNumber}, {'Content-Type': 'application/json'}));
    }

    public async digCell(gameId: string, cellNumber: number): Promise<ActionResponse> {
        return JSON.parse(await this.fetchData('game/dig', 'put', {gameId, cellNumber}, {'Content-Type': 'application/json'}));
    }

    public async endGame(gameId: string): Promise<EventResponse> {
        return JSON.parse(await this.fetchData(`game/end/${gameId}`, 'put', null, {}));
    }

    private async fetchData(path: string, verb: string, request: any, headers: any): Promise<string>{
        const url: string = `${this.host}/${path}`;
        const res: any = await fetch(url, {
            method: verb,
            body: request ? JSON.stringify(request) : '',
            headers: headers
        });

        if (res.ok) {
            const body: any = await res.text();
            if (body.length < 1) { throw new Error('Error: Could not fetch data'); }
            return body;
        } else {
            throw new Error('Error: Could not fetch data');
        }
    }
}

export {
    IMineSweeperClient,
    MineSweeperClient
}