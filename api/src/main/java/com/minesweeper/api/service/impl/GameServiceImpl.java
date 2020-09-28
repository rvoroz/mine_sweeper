package com.minesweeper.api.service.impl;

import java.time.LocalDateTime;

import com.minesweeper.api.factory.FieldFactory;
import com.minesweeper.api.factory.GameFactory;
import com.minesweeper.api.model.common.Field;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.common.enums.GameStatus;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.model.response.GameResponse;
import com.minesweeper.api.model.response.PauseResumeResponse;
import com.minesweeper.api.repository.GameRepository;
import com.minesweeper.api.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public GameResponse startGame(final GameRequest gameRequest) {
        final GameFactory gameFactory = new GameFactory();
        final FieldFactory fieldFactory = new FieldFactory();

        Game newGame = gameFactory.setUserId(gameRequest.getUserId()).setGameConfig(gameRequest.getGameConfig())
                .build();
        gameRepository.save(newGame);
        Field newField = fieldFactory.build(newGame);
        return new GameResponse(newGame.getId(), newGame.getUserId(), newField);
    }

    @Override
    public PauseResumeResponse pauseGame(String gameId) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null || game.getStatus() != GameStatus.IN_PROGRESS) return null;
        game.setPauseDate(LocalDateTime.now());
        game.setStatus(GameStatus.PAUSED);
        gameRepository.save(game);
        return new PauseResumeResponse(game.getId(), game.getPauseDate(), game.getStatus());
    }

    @Override
    public GameResponse resumeGame(String gameId) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null || game.getStatus() != GameStatus.PAUSED) return null;
        game.setStatus(GameStatus.IN_PROGRESS);
        final FieldFactory fieldFactory = new FieldFactory();
        Field field = fieldFactory.build(game);
        return new GameResponse(game.getId(), game.getUserId(), field);
    }

}
