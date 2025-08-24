package com.holis.san01.model;

import lombok.Data;

import java.util.Map;

@Data
public class ApiResponse {
    private boolean success;

    private String message;

    private String code;

    private Object data;

    // Mensagens de Value
    public ApiResponse(boolean success, String message, String code,  Object data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    // Apenas uma mensagem
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.code = null;
        this.data = null;
    }

    // Dados
    public ApiResponse(boolean success, Object data) {
        this.success = success;
        this.message = null;
        this.code = "obj";
        this.data = data;
    }
}
