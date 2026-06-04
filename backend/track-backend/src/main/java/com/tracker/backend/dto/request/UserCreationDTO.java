package com.tracker.backend.dto.request;

import java.util.Date;

public record UserCreationDTO(
		String username,
		String firstName,
		String lastName,
		String email,
		String password,
		Date dateOfBirth
		) {
}
