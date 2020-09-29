package com.minesweeper.api.model.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.minesweeper.api.model.common.enums.GameStatus;
import org.springframework.data.annotation.Id;

public class Game {
    @Id
    private String id;
    private String userId;
    private GameConfig config;
    private LocalDateTime startDate;
    private LocalDateTime pauseDate;
    private LocalDateTime endDateTime;
    private List<Cell> mines;
    private List<Integer> openedCells;
    private List<Integer> flaggedCells;
    private GameStatus status;

    public Game(){
        this.openedCells = new ArrayList<>();
        this.flaggedCells = new ArrayList<>();
    }

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public List<Cell> getMines() {
        return mines;
    }

    public void setMines(List<Cell> mines) {
        this.mines = mines;
    }

    public List<Integer> getOpenedCells() {
        return openedCells;
    }

    public void setOpenedCells(List<Integer> openedCells) {
        this.openedCells = openedCells;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public LocalDateTime getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(LocalDateTime pauseDate) {
        this.pauseDate = pauseDate;
    }

    public List<Integer> getFlaggedCells() {
        return flaggedCells;
    }

    public void setFlaggedCells(List<Integer> flaggedCells) {
        this.flaggedCells = flaggedCells;
    }

    
}
