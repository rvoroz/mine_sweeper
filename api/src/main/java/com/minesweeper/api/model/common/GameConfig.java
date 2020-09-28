package com.minesweeper.api.model.common;

public class GameConfig {
    private int rows;
    private int columns;
    private int mines;
    private static final int MIN_EMPTY_RATIO = 3;

    public boolean isValid(){
        int totalCells = this.rows * this.columns;
        return (totalCells - this.mines) > totalCells / GameConfig.MIN_EMPTY_RATIO;
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

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getTotalCells(){
        return this.rows * this.columns;
    }
    
}
