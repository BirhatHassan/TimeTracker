package com.tracker.backend.dto.response;

public class UserResponseDTO {

	private Long id;
	private String username;
	private String emailAddress;

	public UserResponseDTO(Long id, String username, String emailAddress) {
		this.id = id;
		this.username = username;
		this.emailAddress = emailAddress;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
}