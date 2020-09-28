package com.minesweeper.api.model.request;

import com.minesweeper.api.model.common.GameConfig;

public class GameRequest {
    private String userId;
    private GameConfig gameConfig;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public void setGameConfig(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    
}
