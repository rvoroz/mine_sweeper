package com.minesweeper.api.model.response;

import java.time.LocalDateTime;
import com.minesweeper.api.model.common.enums.GameStatus;

public class PauseResponse {
    private String id;
    private LocalDateTime pauseDate;
    private GameStatus status;

    public PauseResponse(String id, LocalDateTime pauseDate, GameStatus status) {
        this.id = id;
        this.pauseDate = pauseDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(LocalDateTime pauseDate) {
        this.pauseDate = pauseDate;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    
}
