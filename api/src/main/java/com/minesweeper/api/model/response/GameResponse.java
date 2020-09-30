package com.minesweeper.api.model.response;

import com.minesweeper.api.model.common.Field;
import com.minesweeper.api.model.common.enums.GameStatus;

public class GameResponse {
    private String id;
    private String userId;
    private Field field;
    private GameStatus status;
    private String error;

    public GameResponse(String id, String userId, Field field, GameStatus status, String error) {
        this.id = id;
        this.userId = userId;
        this.field = field;
        this.status = status;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
    
    
}
