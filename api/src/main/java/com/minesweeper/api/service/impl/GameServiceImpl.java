package com.minesweeper.api.service.impl;

import com.minesweeper.api.factory.FieldFactory;
import com.minesweeper.api.factory.GameFactory;
import com.minesweeper.api.model.common.Field;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.repository.GameRepository;
import com.minesweeper.api.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Field startGame(final GameRequest gameRequest) {
        final GameFactory gameFactory = new GameFactory();
        final FieldFactory fieldFactory = new FieldFactory();

        Game newGame = gameFactory.setUserId(gameRequest.getUserId())
        .setGameConfig(gameRequest.getGameConfig()).build();

        gameRepository.save(newGame);

        return fieldFactory.build(newGame);

        // TODO Persist the game
    }
    
}
