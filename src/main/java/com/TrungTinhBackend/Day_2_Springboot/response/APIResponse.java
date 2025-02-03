package com.TrungTinhBackend.Day_2_Springboot.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class APIResponse {

    private Long statusCode;

    private String message;

    private Object data;

    private LocalDateTime timestamp;

    public Long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
