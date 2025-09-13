package com.holis.san01.model;

import lombok.Data;

@Data
public class ApiResponse {

    private boolean success;
    private String code;
    private String message;
    private Object data;

    // Mensagens de @Valid
    public ApiResponse(boolean success, String message, String code, Object data) {

        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    // Apenas uma mensagem
    public ApiResponse(boolean success, String message) {

        this.success = success;
        this.message = message;
        this.code = "msg";
        this.data = null;
    }

    // Retorno de dados
    public ApiResponse(boolean success, Object data) {

        this.success = success;
        this.message = null;
        this.code = "obj";
        this.data = data;
    }
}
