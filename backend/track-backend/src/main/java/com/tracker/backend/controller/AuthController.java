package com.tracker.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {
	@RequestMapping("/login")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
