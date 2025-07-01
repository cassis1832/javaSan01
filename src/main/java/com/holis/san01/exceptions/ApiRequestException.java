package com.holis.san01.exceptions;

import java.io.Serial;

/**
 * Erros diversos
 */
public class ApiRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ApiRequestException() {
        super();
    }

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
