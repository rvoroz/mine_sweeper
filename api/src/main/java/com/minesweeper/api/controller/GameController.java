package com.minesweeper.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "game")
public class GameController {

    @RequestMapping(path = "/start", method = RequestMethod.GET)
    public String start(){
        return "game started";
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
