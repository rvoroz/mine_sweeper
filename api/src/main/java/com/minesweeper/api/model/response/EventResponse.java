package com.minesweeper.api.model.response;

import java.time.LocalDateTime;
import com.minesweeper.api.model.common.enums.GameStatus;

import org.springframework.jca.support.LocalConnectionFactoryBean;

public class EventResponse {
    private String id;
    private LocalDateTime pauseDate;
    private LocalDateTime endDate;
    private GameStatus status;

    public EventResponse(String id, LocalDateTime pauseDate, LocalDateTime endDate, GameStatus status) {
        this.id = id;
        this.pauseDate = pauseDate;
        this.status = status;
        this.endDate = endDate;
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    
}
