package com.tracker.backend.service.serviceinterface;

import org.springframework.stereotype.Service;

import com.tracker.backend.dto.request.LoginRequestDTO;
import com.tracker.backend.entity.User;
import com.tracker.backend.exception.auth.AuthException;
import com.tracker.backend.repository.UserRepository;

@Service
public class AuthService {
	public UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User loginUser(LoginRequestDTO loginRequestDTO) {
		User response = userRepository.getUserByEmailAddress(loginRequestDTO.emailAddress());

		if (response == null) {
			throw new AuthException("E-mail address not found");
		}

		if (response != null) {
			return response;
		}

		return null;
	}
}
