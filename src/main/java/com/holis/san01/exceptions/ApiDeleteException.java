package com.holis.san01.exceptions;

import java.io.Serial;

public class ApiDeleteException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public ApiDeleteException() {
		super();
	}

	public ApiDeleteException(String message) {
		super(message);
	}

	public ApiDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

}
