package com.tracker.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tracker.backend.dto.request.UserCreationDTO;
import com.tracker.backend.service.impl.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerAccount(@RequestBody UserCreationDTO userCreationDTO) {
		return ResponseEntity.ok("Account created successfully");
	}

	@GetMapping("/check/username")
	public ResponseEntity<Void> existsByUsername(@RequestParam String username) {
		boolean exists = userService.existsByUsername(username);
		if (exists) {
			return ResponseEntity.status(409).build(); // CONFLICT
		}

		return ResponseEntity.ok().build();
	}

	/**
	 * This endpoint checks if an email is already in use. If it is, it returns a 409 CONFLICT status.
	 * If the email is free, it returns a 200 OK status.
	 */
	@GetMapping("/check/email")
	public ResponseEntity<Void> existsByEmail(@RequestParam String email) {

		boolean exists = userService.existsByEmailAddress(email);

		if (exists) {
			return ResponseEntity.status(409).build(); // CONFLICT
		}

		return ResponseEntity.ok().build(); // email is free
	}
}
