package com.tracker.backend.dto.request;

public record UserCreationDTO(
		String username,
		String firstName,
		String lastName,
		String email,
		String password
		) {
}
