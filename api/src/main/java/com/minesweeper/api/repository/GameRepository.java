package com.minesweeper.api.repository;

import java.util.List;

import com.minesweeper.api.model.common.Game;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
    public List<Game> findByUserId(String userId);
}
