package com.minesweeper.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minesweeper.api.model.common.enums.GameAction;
import com.minesweeper.api.model.request.DigRequest;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.model.response.GameResponse;
import com.minesweeper.api.model.response.PauseResumeResponse;
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
    public ResponseEntity<PauseResumeResponse> pause(@PathVariable("gameId") String gameId){
        PauseResumeResponse response = gameService.pauseGame(gameId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/resume/{gameId}", produces = "application/json")
    public ResponseEntity<PauseResumeResponse> resume(@PathVariable("gameId") String gameId){
        PauseResumeResponse response = gameService.resumeGame(gameId);
        if(response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/dig", consumes = "application/json", produces = "application/json")
    public Object dig(@RequestBody DigRequest digRequest){
        return "cell opened";
    }

    @RequestMapping(path = "/flag", method = RequestMethod.GET)
    public String flag(){
        return "cell flagged";
    }

    @RequestMapping(path = "/end", method = RequestMethod.GET)
    public String end(){
        return "game ended";
    }

}
