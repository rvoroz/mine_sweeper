package com.minesweeper.api.factory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.enums.CellType;
import com.minesweeper.api.model.common.enums.GameStatus;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.common.GameConfig;

public class GameFactory {
    private final Game newGame;
    private final Random random = new Random();

    public GameFactory() {
        this.newGame = new Game();
    }

    public GameFactory setUserId(final String userId) {
        this.newGame.setUserId(userId);
        return this;
    }

    public GameFactory setGameConfig(final GameConfig gameConfig) {
        this.newGame.setConfig(gameConfig);
        return this;
    }

    public Game build() {
        final UUID uuid = UUID.randomUUID();
        this.newGame.setId(uuid.toString());
        this.newGame.setMines(this.getMines());
        this.newGame.setStartDate(LocalDateTime.now());
        this.newGame.setStatus(GameStatus.IN_PROGRESS);
        return this.newGame;
    }

    private List<Cell> getMines(){
        List<Cell> mines = new ArrayList<>();
        final GameConfig config = this.newGame.getConfig();

        while (mines.size() < config.getMines()){
            final int randCell = this.random.nextInt(config.getTotalCells());
            final Cell existingMine = mines.stream().filter(m -> m.getCellNumber() == randCell).findFirst().orElse(null);
            if(existingMine == null){
                Cell newMine = new Cell(CellType.MINE, randCell);
                mines.add(newMine);
            }
        }

        return mines;
    }
}
