package com.minesweeper.api.model.common;

import java.time.LocalTime;
import java.util.List;

public class Game {
    private String id;
    private String userId;
    private GameConfig config;
    private LocalTime startDate;
    private List<Cell> mines;

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

    public LocalTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalTime startDate) {
        this.startDate = startDate;
    }

    public List<Cell> getMines() {
        return mines;
    }

    public void setMines(List<Cell> mines) {
        this.mines = mines;
    }

    
}
