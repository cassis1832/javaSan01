package com.holis.san01.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private String error;

    private String message;

    private Map<String, String> details;

    public ErrorResponse(String error, String message, Map<String, String> details) {
        this.timestamp = LocalDateTime.now();
        this.error = error;
        this.message = message;
        this.details = details;
    }

    public ErrorResponse(String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.error = error;
        this.message = message;
        this.details = null;
    }
}