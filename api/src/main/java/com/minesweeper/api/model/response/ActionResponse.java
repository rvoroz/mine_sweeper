package com.minesweeper.api.model.response;

import java.util.ArrayList;
import java.util.List;

import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.enums.GameStatus;

public class ActionResponse {
    private String gameId;
    private List<Cell> changedCells;
    private GameStatus status;

    public ActionResponse(String gameId, GameStatus status) {
        this.gameId = gameId;
        this.changedCells = new ArrayList<>();
        this.status = status;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<Cell> getChangedCells() {
        return changedCells;
    }

    public void setChangedCells(List<Cell> changedCells) {
        this.changedCells = changedCells;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

        
}
