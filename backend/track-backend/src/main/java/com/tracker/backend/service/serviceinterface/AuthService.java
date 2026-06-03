package com.tracker.backend.service.serviceinterface;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tracker.backend.dto.request.LoginRequestDTO;
import com.tracker.backend.dto.response.LoginResponseDTO;
import com.tracker.backend.entity.User;
import com.tracker.backend.exception.auth.AuthException;
import com.tracker.backend.repository.UserRepository;

@Service
public class AuthService {
	public UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
		LoginResponseDTO response = userRepository.getUserByEmailAddress(loginRequestDTO.emailAddress());

		if (response == null) {
			throw new AuthException("E-mail address not found");
		}

		if (response != null && response.password().matches(loginRequestDTO.password())) {
			return response;
		}

		return null;
	}
}
