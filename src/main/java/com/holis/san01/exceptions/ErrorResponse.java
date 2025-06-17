package com.holis.san01.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponse {

    // Formato padrão para respostas de erro
    private int status;
    private String error;
    private String message;
    private Map<String, String> details; // Usado apenas para validações
}