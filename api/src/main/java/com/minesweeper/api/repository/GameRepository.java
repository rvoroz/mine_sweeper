package com.minesweeper.api.repository;

import com.minesweeper.api.model.common.Game;

public interface GameRepository {
    public Game getGameById(String id);
    public void saveGame(Game game);
}
