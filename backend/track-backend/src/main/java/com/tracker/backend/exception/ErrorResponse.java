package com.tracker.backend.exception;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private final String message;
	private final String error;
	private final String path;
	private final LocalDateTime timestamp;

	public ErrorResponse(String message, String error, String path) {
		this.message = message;
		this.error = error;
		this.path = path;
		this.timestamp = LocalDateTime.now();
	}
}

