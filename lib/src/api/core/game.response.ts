import GameConfig from './game.configuration'

interface GameResponse {
    userId: string;
    gameConfig: GameConfig;
}

export default GameResponse;