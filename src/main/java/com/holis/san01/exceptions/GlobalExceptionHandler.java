package com.holis.san01.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // ------ Tratamento de Validação (@Valid) ------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        System.out.println("+++++++++++++++++++ Exception validação ");

        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> Optional.of(error.getDefaultMessage()).orElse("Erro de validação")
                ));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT,
                        "Erro de Validação",
                        "Campos inválidos na requisição",
                        errors
                ));
    }

    @ExceptionHandler(NotFoundRequestException.class)
    public ResponseEntity<ErrorResponse> notFoundHandler(NotFoundRequestException ex) {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX - Not Found Exception - OK ");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND,
                        "Recurso Não Encontrado",
                        ex.getMessage(),
                        null
                ));
    }

    // ------ Recursos Não Encontrados (404) ------
    @ExceptionHandler({ChangeSetPersister.NotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
        System.out.println("************************** -not found Exception - sem tratamento");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND,
                        "Recurso Não Encontrado - não previsto",
                        ex.getMessage(),
                        null
                ));
    }

    // Conflito no DELETE
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(ResourceConflictException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT,
                        "Conflito no delete",
                        ex.getMessage(),
                        null
                ));
    }

    // ------ Acesso Negado (403) ------
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.FORBIDDEN,
                        "Acesso Negado",
                        "Você não tem permissão para este recurso",
                        null
                ));
    }

    // ------ Conflitos no Banco de Dados (409) ------
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Conflito de Dados",
                        "Violação de integridade (ex: registro duplicado)",
                        null
                ));
    }

    // ------ JSON Inválido (400) ------
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "JSON Inválido",
                        "Formato da requisição incorreto",
                        null
                ));
    }

    // ------ Erros Internos (500) ------
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleInternalErrors(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Erro Interno",
                        "Ocorreu um erro inesperado",
                        null
                ));
    }

    // ------ Catch-all para outras exceções ------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Erro Geral",
                        "Falha não mapeada: " + ex.getClass().getSimpleName(),
                        null
                ));
    }
}
