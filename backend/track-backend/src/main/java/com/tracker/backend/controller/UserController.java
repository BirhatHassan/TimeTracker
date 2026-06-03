package com.tracker.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.backend.dto.request.UserCreationDTO;
import com.tracker.backend.service.impl.UserService;

@RestController
@RequestMapping("user/")
public class UserController {
	private UserService userService;

	@RequestMapping("register")
	public ResponseEntity<String> registerAccount(@RequestBody UserCreationDTO userCreationDTO) {
		return ResponseEntity.ok("Account created successfully");
	}
}
