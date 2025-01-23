package com.holis.san01.exceptions;

public class ApiDeleteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ApiDeleteException() {
		super();
	}

	public ApiDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiDeleteException(String message) {
		super(message);
	}
}
