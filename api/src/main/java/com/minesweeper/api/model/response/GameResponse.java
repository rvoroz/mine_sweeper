package com.minesweeper.api.model.response;

import com.minesweeper.api.model.common.Field;

public class GameResponse {
    private String id;
    private String userId;
    private Field field;

    public GameResponse(String id, String userId, Field field) {
        this.id = id;
        this.userId = userId;
        this.field = field;
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
    

}
