package com.minesweeper.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.minesweeper.api.model.request.GameRequest;
import com.minesweeper.api.service.GameService;

@RestController
@RequestMapping(path = "game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping(path = "/start", consumes = "application/json", produces = "application/json")
    public Object start(@RequestBody GameRequest gameRequest){
        return gameService.startGame(gameRequest);
    }

    @RequestMapping(path = "/resume", method = RequestMethod.GET)
    public String resume(){
        return "game resumed";
    }

    @RequestMapping(path = "/dig", method = RequestMethod.GET)
    public String dig(){
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
