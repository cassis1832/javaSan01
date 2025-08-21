package com.holis.san01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ------ Tratamento de Validação (@Valid) ------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("handleValidationExceptions");
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            erros.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        "Erros de Validação",
                        ex.getMessage(),
                        erros
                ));
    }

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ErrorResponse> ApiHandler(ApiRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorResponse(
                        "Mensagem da Api",
                        ex.getMessage()
                ));
    }
}
