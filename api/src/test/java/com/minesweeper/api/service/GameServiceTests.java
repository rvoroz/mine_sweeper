package com.minesweeper.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.minesweeper.api.model.common.Game;
import com.minesweeper.api.model.common.GameConfig;
import com.minesweeper.api.model.common.enums.CellType;
import com.minesweeper.api.model.common.Cell;
import com.minesweeper.api.model.common.enums.GameStatus;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.model.response.ActionResponse;
import com.minesweeper.api.model.response.EventResponse;
import com.minesweeper.api.model.response.GameResponse;
import com.minesweeper.api.repository.GameRepository;
import com.minesweeper.api.service.impl.GameServiceImpl;
import com.minesweeper.api.test.GameServiceHelper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    GameService gameService = new GameServiceImpl();

    @Test
    public void shouldCreateValidGame() {
        GameRequest request = GameServiceHelper.getGameRequest(2);

        GameResponse gameResponse = this.gameService.startGame(request);
        verify(gameRepository).save(any(Game.class));
        assertEquals(9, gameResponse.getField().getTotalCells());
        assertEquals(2,
                gameResponse.getField().getCells().stream().filter(f -> f.getCellType() == CellType.MINE).count());
    }

    @Test
    public void shouldFailWithInvalidGameError() {
        GameRequest request = GameServiceHelper.getGameRequest(7);
        GameResponse gameResponse = this.gameService.startGame(request);
        verify(gameRepository, never()).save(any(Game.class));
        assertNull(gameResponse.getField());
        assertEquals("Invalid Game. Few empty rows", gameResponse.getError());
    }

    @Test
    public void shouldPauseGameCorrectly() {
        Game game = GameServiceHelper.getGame(GameStatus.IN_PROGRESS, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        EventResponse response = gameService.pauseGame(game.getId());
        verify(gameRepository).findById(anyString());
        verify(gameRepository).save(any(Game.class));
        assertEquals(GameStatus.PAUSED, response.getStatus());
        assertEquals(game.getId(), response.getId());
        assertNotNull(response.getPauseDate());
    }

    @Test
    public void shouldReturnErrorOnPauseWithNonExistentGame() {
        Game game = GameServiceHelper.getGame(GameStatus.GAME_OVER, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        EventResponse response = gameService.pauseGame("321");
        verify(gameRepository).findById(anyString());
        verify(gameRepository, never()).save(any(Game.class));
        assertNull(response);
    }

    @Test
    public void shouldResumeGameCorrectly() {
        Game game = GameServiceHelper.getGame(GameStatus.PAUSED, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        GameResponse response = gameService.resumeGame(game.getId());
        verify(gameRepository).findById(anyString());
        verify(gameRepository).save(any(Game.class));
        assertNotNull(response.getField());
        assertEquals(game.getId(), response.getId());
    }

    @Test
    public void shouldReturnErrorOnResumeWithNonExistentGame() {
        Game game = GameServiceHelper.getGame(GameStatus.COMPLETED, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        EventResponse response = gameService.pauseGame("321");
        verify(gameRepository).findById(anyString());
        verify(gameRepository, never()).save(any(Game.class));
        assertNull(response);
    }

    @Test
    public void shouldEndGameCorrectly() {
        Game game = GameServiceHelper.getGame(GameStatus.IN_PROGRESS, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        EventResponse response = gameService.endGame(game.getId());
        verify(gameRepository).findById(anyString());
        verify(gameRepository).save(any(Game.class));
        assertEquals(GameStatus.GAME_OVER, response.getStatus());
        assertEquals(game.getId(), response.getId());
        assertNotNull(response.getEndDate());
    }

    @Test
    public void shouldReturnErrorOnEndWithNonExistentGame() {
        EventResponse response = gameService.pauseGame("321");
        verify(gameRepository).findById(anyString());
        verify(gameRepository, never()).save(any(Game.class));
        assertNull(response);
    }

    @Test
    public void shouldFlagCellCorrectly(){
        Game game = GameServiceHelper.getGame(GameStatus.IN_PROGRESS, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        ActionResponse response = gameService.flagCell(game.getId(), 6);
        verify(gameRepository).findById(anyString());
        verify(gameRepository).save(any(Game.class));
        assertNotNull(response.getChangedCells());
        assertEquals(game.getId(), response.getGameId());
        assertEquals(1, response.getChangedCells().size());
        assertEquals(GameStatus.IN_PROGRESS, response.getStatus());
    }

    @Test
    public void shouldReturnErrorOnFlagWithNonExistentGame(){
        Game game = GameServiceHelper.getGame(GameStatus.COMPLETED, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        ActionResponse response = gameService.flagCell(game.getId(), 1);
        verify(gameRepository).findById(anyString());
        verify(gameRepository, never()).save(any(Game.class));
        assertNull(response);
    }

    @Test
    public void shouldEndGameWhenDiggingMineCell(){
        Game game = GameServiceHelper.getGame(GameStatus.IN_PROGRESS, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        ActionResponse response = gameService.digCell(game.getId(), 1);
        verify(gameRepository).findById(anyString());
        verify(gameRepository).save(any(Game.class));
        assertNotNull(response.getChangedCells());
        assertEquals(game.getId(), response.getGameId());
        assertEquals(1, response.getChangedCells().size());
        assertEquals(GameStatus.GAME_OVER, response.getStatus());
    }

    @Test
    public void shouldDigCellsCorrectly(){
        Game game = GameServiceHelper.getGame(GameStatus.IN_PROGRESS, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        ActionResponse response = gameService.digCell(game.getId(), 6);
        verify(gameRepository).findById(anyString());
        verify(gameRepository).save(any(Game.class));
        assertNotNull(response.getChangedCells());
        assertEquals(game.getId(), response.getGameId());
        assertEquals(6, response.getChangedCells().size());
        assertEquals(GameStatus.IN_PROGRESS, response.getStatus());
    }

    @Test
    public void shouldReturnErrorOnDigWithNonExistentGame(){
        Game game = GameServiceHelper.getGame(GameStatus.COMPLETED, 2);
        when(gameRepository.findById(anyString())).thenReturn(Optional.of(game));
        ActionResponse response = gameService.digCell(game.getId(), 1);
        verify(gameRepository).findById(anyString());
        verify(gameRepository, never()).save(any(Game.class));
        assertNull(response);
    }
}
