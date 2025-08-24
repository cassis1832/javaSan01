package com.holis.san01.exceptions;

import java.io.Serial;

/**
 * Não encontrado
 */
public class NotFoundRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundRequestException(String message) {
        super(message);
    }
}
