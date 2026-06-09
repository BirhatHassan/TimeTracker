package com.tracker.backend.service.serviceinterface;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tracker.backend.config.JWTConfig;
import com.tracker.backend.dto.request.LoginRequestDTO;
import com.tracker.backend.dto.response.LoginResponseDTO;
import com.tracker.backend.dto.response.UserResponseDTO;
import com.tracker.backend.entity.User;
import com.tracker.backend.exception.auth.AuthException;
import com.tracker.backend.repository.UserRepository;
import com.tracker.backend.service.impl.UserService;
import com.tracker.backend.utils.JWToken;

@Service
public class AuthService {
	public UserRepository userRepository;
	private final UserService userService;
	private final JWTConfig jwtConfig;
	private final PasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepository, UserService userService, JWTConfig jwtConfig, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.jwtConfig = jwtConfig;
		this.passwordEncoder = passwordEncoder;
	}

	public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
		User user = userRepository.getUserByEmailAddress(loginRequestDTO.email());

		if (user == null) {
			throw new AuthException("E-mail address not found");
		}

		if (!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
			throw new AuthException("Invalid credentials");
		}

		JWToken jwToken = new JWToken(user.getId(), user.getUsername());
		String token = jwToken.encode(this.jwtConfig.getIssuer(),
				this.jwtConfig.getPassphrase(),
				this.jwtConfig.getTokenDurationOfValidity()
		);

		return new LoginResponseDTO(user.getId(), user.getUsername(), token);
	}

	public UserResponseDTO getMe(String jwt) {

		JWToken token;

		try {
			token = JWToken.verifyToken(jwt, jwtConfig.getPassphrase());
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
		}

		Long userId = token.getUserId();

		return userService.getMe(userId);
	}
}
