package com.minesweeper.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.minesweeper.api.model.request.ActionRequest;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.model.response.ActionResponse;
import com.minesweeper.api.model.response.GameResponse;
import com.minesweeper.api.model.response.PauseResponse;
import com.minesweeper.api.service.GameService;

@RestController
@RequestMapping(path = "game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping(path = "/start", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GameResponse> start(@RequestBody GameRequest gameRequest){
        GameResponse response = gameService.startGame(gameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/pause/{gameId}", produces = "application/json")
    public ResponseEntity<PauseResponse> pause(@PathVariable("gameId") String gameId){
        PauseResponse response = gameService.pauseGame(gameId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/resume/{gameId}", produces = "application/json")
    public ResponseEntity<GameResponse> resume(@PathVariable("gameId") String gameId){
        GameResponse response = gameService.resumeGame(gameId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/dig", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ActionResponse> dig(@RequestBody ActionRequest digRequest){
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/flag", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ActionResponse> flag(@RequestBody ActionRequest flagRequest){
        ActionResponse response = gameService.flagCell(flagRequest.getGameId(), flagRequest.getCellNumber());
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/end", method = RequestMethod.GET)
    public String end(){
        return "game ended";
    }

}
