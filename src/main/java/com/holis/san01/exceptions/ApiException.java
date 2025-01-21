package com.holis.san01.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException {

	private final HttpStatus httpStatus;
	private final String message;
}
