package com.minesweeper.api.factory;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.CellType;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.common.GameConfig;

import org.apache.tomcat.jni.Local;

public class GameFactory {
    private final Game newGame;
    private final Random random = new Random();

    public GameFactory() {
        this.newGame = new Game();
    }

    public GameFactory userId(final String userId) {
        this.newGame.setUserId(userId);
        return this;
    }

    public GameFactory gameConfig(final GameConfig gameConfig) {
        this.newGame.setConfig(gameConfig);
        return this;
    }

    public Game build() {
        final UUID uuid = UUID.randomUUID();
        this.newGame.setId(uuid.toString());
        this.newGame.setMines(this.getMines());
        this.newGame.setStartDate(LocalTime.now());
        return this.newGame;
    }

    private List<Cell> getMines(){
        List<Cell> mines = new ArrayList<>();
        final GameConfig config = this.newGame.getConfig();

        while (mines.size() < config.getMines()){
            final int randCell = this.random.nextInt(config.getTotalCells() + 1);
            final Cell existingMine = mines.stream().filter(m -> m.getCellNumber() == randCell).findFirst().orElse(null);
            if(existingMine == null){
                int yPos = randCell / config.getColumns();
                int xPos = randCell % config.getColumns();
                Cell newMine = new Cell(CellType.MINE, randCell, xPos, yPos);
                mines.add(newMine);
            }
        }

        return mines;
    }
}
