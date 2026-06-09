package com.tracker.backend.dto.response;

public record LoginResponseDTO(
		Long userId,
		String username,
		String token
) {
}
