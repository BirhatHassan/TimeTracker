package com.tracker.backend.dto.request;

public record LoginRequestDTO(
		String emailAddress,
		String password
) {
}
