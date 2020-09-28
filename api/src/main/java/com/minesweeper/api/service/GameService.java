package com.minesweeper.api.service;

import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.model.response.GameResponse;
import com.minesweeper.api.model.response.PauseResumeResponse;

public interface GameService {
    public GameResponse startGame(GameRequest gameRequest);
    public PauseResumeResponse pauseGame(String gameId);
    public GameResponse resumeGame(String gameId);
}
