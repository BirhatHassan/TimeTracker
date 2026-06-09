package com.tracker.backend.service.serviceinterface;

import org.springframework.stereotype.Service;

import com.tracker.backend.config.JWTConfig;
import com.tracker.backend.dto.request.LoginRequestDTO;
import com.tracker.backend.dto.response.LoginResponseDTO;
import com.tracker.backend.entity.User;
import com.tracker.backend.exception.auth.AuthException;
import com.tracker.backend.repository.UserRepository;
import com.tracker.backend.utils.JWToken;

@Service
public class AuthService {
	public UserRepository userRepository;
	private final JWTConfig jwtConfig;

	public AuthService(UserRepository userRepository, JWTConfig jwtConfig) {
		this.userRepository = userRepository;
		this.jwtConfig = jwtConfig;
	}

	public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
		User user = userRepository.getUserByEmailAddress(loginRequestDTO.email());

		if (user == null) {
			throw new AuthException("E-mail address not found");
		}

		JWToken jwToken = new JWToken(user.getId(), user.getUsername());
		String token = jwToken.encode(this.jwtConfig.getIssuer(),
				this.jwtConfig.getPassphrase(),
				this.jwtConfig.getTokenDurationOfValidity()
		);

		return new LoginResponseDTO(user.getId(), user.getUsername(), token);
	}
}
