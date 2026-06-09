package com.tracker.backend.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.tracker.backend.dto.request.UserCreationDTO;
import com.tracker.backend.dto.response.UserResponseDTO;
import com.tracker.backend.entity.User;
import com.tracker.backend.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*
	 * This method registers a user, it uses BCrypt to hash the password. Very nice!
	 */
	public User registerUser(@RequestBody UserCreationDTO userCreationDTO) {
		String password = userCreationDTO.password();
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		User user = new User();
		user.setUsername(userCreationDTO.username());
		user.setFirstName(userCreationDTO.firstName());
		user.setLastName(userCreationDTO.lastName());
		user.setEmailAddress(userCreationDTO.email());
		user.setPassword(hashedPassword);

		return userRepository.save(user);
	}

	public UserResponseDTO getMe(Long userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() ->
						new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

		return new UserResponseDTO(
				user.getId(),
				user.getUsername(),
				user.getEmailAddress()
		);
	}

	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public Boolean existsByEmailAddress(String emailAddress) {
		return userRepository.existsByEmailAddress(emailAddress);
	}
}
