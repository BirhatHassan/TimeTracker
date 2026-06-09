package com.tracker.backend.dto.request;

public record LoginRequestDTO(
		String email,
		String password
) {
}
