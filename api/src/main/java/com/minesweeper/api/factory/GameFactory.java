package com.minesweeper.api.factory;

import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.common.GameConfig;

public class GameFactory {
    private Game newGame;

    public GameFactory(){
        this.newGame = new Game();
    }

    public GameFactory userId(String userId){
        this.newGame.setUserId(userId);
        return this;
    }

    public GameFactory gameConfig(GameConfig gameConfig){
        this.newGame.setConfig(gameConfig);
        return this;
    }

    public Game build(){
        return this.newGame;
    }
}
