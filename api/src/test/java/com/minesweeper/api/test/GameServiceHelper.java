package com.minesweeper.api.test;

import java.util.ArrayList;
import java.util.List;

import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.common.GameConfig;
import com.minesweeper.api.model.common.enums.CellType;
import com.minesweeper.api.model.common.enums.GameStatus;
import com.minesweeper.api.model.request.GameRequest;

public class GameServiceHelper {
    public static GameRequest getGameRequest(int mines) {
        GameRequest request = new GameRequest();
        request.setUserId("123");
        request.setGameConfig(GameServiceHelper.getGameConfig(mines));
        return request;
    }

    public static Game getGame(GameStatus status, int mines) {
        Game game = new Game();
        game.setId("1234567890");
        game.setStatus(status);
        game.setConfig(GameServiceHelper.getGameConfig(mines));
        game.setMines(GameServiceHelper.getMines(mines));
        return game;
    }

    public static GameConfig getGameConfig(int mines) {
        GameConfig config = new GameConfig();
        config.setRows(3);
        config.setColumns(3);
        config.setMines(mines);
        return config;
    }

    /*
     * private Field getField(int mines){ Field field = new Field();
     * field.setTotalCells(9); field.setColumns(3); field.setRows(3);
     * field.setCells(GameServiceHelper.getCells(mines)); return field; }
     * 
     * private List<Cell> getCells(int mines){ int emptyCells = (9 - mines);
     * List<Cell> cells = new ArrayList<>(); for(int i = 0; i < emptyCells; i++){
     * cells.add(new Cell(CellType.BLANK, i)); }
     * 
     * for(int i = 0; i < mines; i++){ cells.add(new Cell(CellType.MINE, emptyCells
     * + i)); }
     * 
     * return cells; }
     */

    public static List<Cell> getMines(int mines) {
        List<Cell> cells = new ArrayList<>();
        for(int i = 0; i < mines; i++){
            cells.add(new Cell(CellType.MINE, i));
        }

        return cells;
    }
}
