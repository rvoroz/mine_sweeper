package com.minesweeper.api.service.impl;

import java.time.LocalDateTime;
import com.minesweeper.api.factory.FieldFactory;
import com.minesweeper.api.factory.GameFactory;
import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.Field;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.common.enums.CellType;
import com.minesweeper.api.model.common.enums.GameStatus;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.model.response.ActionResponse;
import com.minesweeper.api.model.response.GameResponse;
import com.minesweeper.api.model.response.PauseResponse;
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
    public PauseResponse pauseGame(String gameId) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null || game.getStatus() != GameStatus.IN_PROGRESS)
            return null;

        game.setPauseDate(LocalDateTime.now());
        game.setStatus(GameStatus.PAUSED);
        gameRepository.save(game);
        return new PauseResponse(game.getId(), game.getPauseDate(), game.getStatus());
    }

    @Override
    public GameResponse resumeGame(String gameId) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null || game.getStatus() != GameStatus.PAUSED)
            return null;

        game.setStatus(GameStatus.IN_PROGRESS);
        gameRepository.save(game);
        final FieldFactory fieldFactory = new FieldFactory();
        Field field = fieldFactory.build(game);
        return new GameResponse(game.getId(), game.getUserId(), field);
    }

    @Override
    public ActionResponse flagCell(String gameId, int cellNumber) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null || game.getStatus() != GameStatus.IN_PROGRESS)
            return null;
        
        Cell cell = game.getMines().stream().filter(m -> m.getCellNumber() == cellNumber).findFirst().orElse(null);
        if(cell != null){
            cell.setFlag(!cell.isFlag());
        } else {
            cell = new Cell(CellType.BLANK, cellNumber);
            if(game.getFlaggedCells().contains(cellNumber)) {
                game.getFlaggedCells().remove(cellNumber);
                cell.setFlag(false);
            } else {
                game.getFlaggedCells().add(cellNumber);
                cell.setFlag(true);
            } 
        }

        ActionResponse actionResponse = new ActionResponse(game.getId(), game.getStatus());
        actionResponse.getChangedCells().add(cell);

        return actionResponse;
    }

    @Override
    public ActionResponse digCell(String gameId, int cellNumber) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null || game.getStatus() != GameStatus.IN_PROGRESS)
            return null;

        final FieldFactory fieldFactory = new FieldFactory();
        Field field 
    }

}
