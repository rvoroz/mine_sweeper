import GameConfig from './game.configuration'

interface GameRequest {
    userId: string;
    gameConfig: GameConfig;
}

export default GameRequest;