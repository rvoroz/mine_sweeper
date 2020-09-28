package com.minesweeper.api.service.impl;

import com.minesweeper.api.factory.FieldFactory;
import com.minesweeper.api.factory.GameFactory;
import com.minesweeper.api.model.common.Field;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public Field startGame(final GameRequest gameRequest) {
        final GameFactory gameFactory = new GameFactory();
        final FieldFactory fieldFactory = new FieldFactory();

        Game newGame = gameFactory.setUserId(gameRequest.getUserId())
        .setGameConfig(gameRequest.getGameConfig()).build();

        return fieldFactory.build(newGame);

        // TODO Persist the game
    }
    
}
