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

}
