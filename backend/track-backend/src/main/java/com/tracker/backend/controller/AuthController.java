package com.tracker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tracker.backend.config.JWTConfig;
import com.tracker.backend.dto.request.LoginRequestDTO;
import com.tracker.backend.dto.response.LoginResponseDTO;
import com.tracker.backend.service.serviceinterface.AuthService;
import com.tracker.backend.utils.CookieUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;
	private final JWTConfig jwtConfig;

	@Autowired
	public AuthController(AuthService authService, JWTConfig jwtConfig) {
		this.authService = authService;
		this.jwtConfig = jwtConfig;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		LoginResponseDTO response = authService.loginUser(loginRequestDTO);

		ResponseCookie cookie = CookieUtils.createJwtCookie(response.token(), jwtConfig.getTokenDurationOfValidity());

		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(response);
	}
}
