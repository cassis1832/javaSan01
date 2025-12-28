package com.holis.san01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse02<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ApiResponse02<T> success(String message) {
        return new ApiResponse02<>(true, message, null, LocalDateTime.now());
    }

    public static <T> ApiResponse02<T> success(T data) {
        return new ApiResponse02<>(true, null, data, LocalDateTime.now());
    }

    public static <T> ApiResponse02<T> success(T data, String message) {
        return new ApiResponse02<>(true, message, data, LocalDateTime.now());
    }

    public static <T> ApiResponse02<T> errorValid(T data) {
        return new ApiResponse02<>(false, "errorValid", data, LocalDateTime.now());
    }

    public static <T> ApiResponse02<T> errorMessage(String message) {
        return new ApiResponse02<>(false, message, null, LocalDateTime.now());
    }
}
