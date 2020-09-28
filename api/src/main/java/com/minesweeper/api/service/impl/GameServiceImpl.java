package com.minesweeper.api.service.impl;

import com.minesweeper.api.factory.GameFactory;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public Game startGame(final GameRequest gameRequest) {
        final GameFactory gameFactory = new GameFactory();
        return gameFactory.userId(gameRequest.getUserId())
        .gameConfig(gameRequest.getGameConfig()).build();

        // TODO Persiste the game
    }
    
}
