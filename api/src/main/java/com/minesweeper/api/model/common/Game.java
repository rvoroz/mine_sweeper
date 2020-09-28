package com.minesweeper.api.model.database;

import com.minesweeper.api.model.common.GameConfig;

public class Game {
    private String id;
    private String userId;
    private GameConfig config;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public GameConfig getConfig() {
        return config;
    }

    public void setConfig(GameConfig config) {
        this.config = config;
    }
}
