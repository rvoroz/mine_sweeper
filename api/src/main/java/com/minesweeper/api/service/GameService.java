package com.minesweeper.api.service;

import com.minesweeper.api.model.common.Field;
import com.minesweeper.api.model.request.GameRequest;

public interface GameService {
    public Field startGame(GameRequest gameRequest);
}
