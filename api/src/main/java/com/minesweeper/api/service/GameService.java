package com.minesweeper.api.service;

import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.model.response.ActionResponse;
import com.minesweeper.api.model.response.GameResponse;
import com.minesweeper.api.model.response.EventResponse;

public interface GameService {
    public GameResponse startGame(GameRequest gameRequest);
    public EventResponse pauseGame(String gameId);
    public GameResponse resumeGame(String gameId);
    public ActionResponse flagCell(String gameId, int cellNumber);
    public ActionResponse digCell(String gameId, int cellNumber);
	public EventResponse endGame(String gameId);
}
