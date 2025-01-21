package com.holis.san01.exceptions;

public class ApiRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ApiRequestException() {
		super();
	}

	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiRequestException(String message) {
		super(message);
	}
}
