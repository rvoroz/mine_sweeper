package com.minesweeper.api.repository;

import com.minesweeper.api.model.common.Game;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {

}
