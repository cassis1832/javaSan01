package com.holis.san01.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	//
	@ExceptionHandler(value = { NotFoundRequestException.class })
	public ResponseEntity<Object> handleNotFoundException(
			NotFoundRequestException e) {

		ApiException erro = new ApiException(
				HttpStatus.NOT_FOUND,
				e.getMessage());

		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}

	//
	@ExceptionHandler(value = { ApiRequestException.class })
	public ResponseEntity<Object> handleApiRequestException(
			ApiRequestException e) {

		ApiException erro = new ApiException(
				HttpStatus.BAD_REQUEST,
				e.getMessage());

		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}

	// 500
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(
			Exception e, WebRequest request) {

		ApiException erro = new ApiException(
				HttpStatus.INTERNAL_SERVER_ERROR,
				e.getMessage());

		return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException e,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {

		ApiException error = new ApiException(
				HttpStatus.BAD_REQUEST,
				e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleBindException(
			final BindException e,
			final HttpHeaders headers,
			final HttpStatus status,
			final WebRequest request) {

		ApiException error = new ApiException(
				HttpStatus.BAD_REQUEST,
				e.getMessage());

		return handleExceptionInternal(e, error, headers, error.getHttpStatus(), request);
	}
}
