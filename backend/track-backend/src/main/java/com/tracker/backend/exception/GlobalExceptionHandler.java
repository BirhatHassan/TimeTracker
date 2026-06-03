package com.tracker.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tracker.backend.exception.auth.AuthException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(
			Exception ex,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		ErrorResponse error = new ErrorResponse(
				ex.getMessage(),
				status.getReasonPhrase(),
				request.getRequestURI()
		);

		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ErrorResponse> handleInvalidPassword(
			AuthException ex,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;

		ErrorResponse error = new ErrorResponse(
				ex.getMessage(),
				status.getReasonPhrase(),
				request.getRequestURI()
		);

		return new ResponseEntity<>(error, status);
	}
}