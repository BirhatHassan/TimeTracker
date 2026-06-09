package com.tracker.backend.controller;

import com.tracker.backend.dto.response.SteamUserDTO;
import com.tracker.backend.service.impl.SteamApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/steam")
public class SteamApiController {

	private final SteamApiService steamApiService;

	public SteamApiController(SteamApiService steamApiService) {
		this.steamApiService = steamApiService;
	}

	@GetMapping("/user/{steamId}")
	public ResponseEntity<SteamUserDTO> getSteamUser(@PathVariable String steamId) {
		SteamUserDTO response = steamApiService.getSteamUser(steamId);
		return ResponseEntity.ok(response);
	}
}