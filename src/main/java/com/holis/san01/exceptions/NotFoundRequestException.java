package com.holis.san01.exceptions;

/*
    Recurso não encontrado
 */
public class NotFoundRequestException extends RuntimeException {

    public NotFoundRequestException() {
        super("Recurso não encontrado");
    }

    public NotFoundRequestException(String message) {
        super(message);
    }

    public NotFoundRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
