package com.minesweeper.api.service;

import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.request.GameRequest;

public interface GameService {
    public Game startGame(GameRequest gameRequest);
}
