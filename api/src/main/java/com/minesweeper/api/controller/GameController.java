package com.minesweeper.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.minesweeper.api.model.request.ActionRequest;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.model.response.ActionResponse;
import com.minesweeper.api.model.response.GameResponse;
import com.minesweeper.api.model.response.EventResponse;
import com.minesweeper.api.service.GameService;

@RestController
@RequestMapping(path = "game")
public class GameController {
    @Autowired
    private GameService gameService;

    @CrossOrigin(origins= "*")
    @PostMapping(path = "/start", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GameResponse> start(@RequestBody GameRequest gameRequest){
        GameResponse response = gameService.startGame(gameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @CrossOrigin(origins= "*")
    @PutMapping(path = "/pause/{gameId}", produces = "application/json")
    public ResponseEntity<EventResponse> pause(@PathVariable("gameId") String gameId){
        EventResponse response = gameService.pauseGame(gameId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins= "*")
    @PutMapping(path = "/resume/{gameId}", produces = "application/json")
    public ResponseEntity<GameResponse> resume(@PathVariable("gameId") String gameId){
        GameResponse response = gameService.resumeGame(gameId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins= "*")
    @PutMapping(path = "/dig", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ActionResponse> dig(@RequestBody ActionRequest digRequest){
        ActionResponse response = gameService.digCell(digRequest.getGameId(), digRequest.getCellNumber());
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins= "*")
    @PutMapping(path = "/flag", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ActionResponse> flag(@RequestBody ActionRequest flagRequest){
        ActionResponse response = gameService.flagCell(flagRequest.getGameId(), flagRequest.getCellNumber());
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins= "*")
    @GetMapping(path = "/end/{gameId}", produces = "application/json")
    public ResponseEntity<EventResponse> end(@PathVariable("gameId") String gameId){
        EventResponse response = gameService.endGame(gameId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins= "*")
    @GetMapping(path = "/{gameId}", produces = "application/json")
    public ResponseEntity<GameResponse> getById(@PathVariable("gameId") String gameId){
        GameResponse response = gameService.getGameById(gameId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins= "*")
    @GetMapping(path = "user/{userId}", produces = "application/json")
    public ResponseEntity<List<EventResponse>> getGamesByUserId(@PathVariable("userId") String userId){
        List<EventResponse> response = gameService.getGamesByUserId(userId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }
}
