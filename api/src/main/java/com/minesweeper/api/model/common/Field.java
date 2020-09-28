package com.minesweeper.api.model.common;

import java.util.List;

public class Field {
    private String gameId;
    private String userId;
    private int totalCells;
    private int rows;
    private int columns;
    private List<Cell> cells;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public void setTotalCells(int totalCells) {
        this.totalCells = totalCells;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
