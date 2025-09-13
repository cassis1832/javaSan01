package com.holis.san01.exceptions;

import com.holis.san01.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Tratamento de Validação (@Valid)
     * Devolve HTTPSTATUS.OK para não subir excessão nem dar erro na console do Angular
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            erros.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(
                        false,
                        "Erros @Valid",
                        "map",
                        erros
                ));
    }

    /**
     * Mensagem informativa
     * Devolve HTTPSTATUS.OK para não subir excessão nem dar erro na console do Angular
     */
    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ApiResponse> ApiHandler(ApiRequestException ex) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(
                        false,
                        ex.getMessage()
                ));
    }

    /**
     * Not Found
     * Devolve HTTPSTATUS.OK para não subir excessão nem dar erro na console do Angular
     */
    @ExceptionHandler(NotFoundRequestException.class)
    public ResponseEntity<ApiResponse> NotFoundException(NotFoundRequestException ex) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(
                        false,
                        ex.getMessage()
                ));
    }
}
