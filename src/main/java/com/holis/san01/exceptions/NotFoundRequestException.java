package com.holis.san01.exceptions;

public class NotFoundRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundRequestException() {
		super();
	}

	public NotFoundRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundRequestException(String message) {
		super(message);
	}
}
