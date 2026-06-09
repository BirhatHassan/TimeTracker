package com.tracker.backend.service.impl;

import com.tracker.backend.dto.response.SteamUserDTO;
import com.tracker.backend.dto.response.SteamUserWrapperDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SteamApiService {

	private final WebClient webClient;

	@Value("${steam.api.base-url}")
	private String steamApiBaseUrl;

	@Value("${steam.api.key}")
	private String steamApiKey;

	public SteamApiService(WebClient webClient) {
		this.webClient = webClient;
	}

	public SteamUserDTO getSteamUser(String steamId) {

		String url = STR."\{steamApiBaseUrl}/ISteamUser/GetPlayerSummaries/v2/?key=\{steamApiKey}&steamids=\{steamId}";

		SteamUserWrapperDTO response = webClient.get()
				.uri(url)
				.retrieve()
				.bodyToMono(SteamUserWrapperDTO.class)
				.block();

		if (response == null
				|| response.getResponse() == null
				|| response.getResponse().getPlayers() == null
				|| response.getResponse().getPlayers().isEmpty()) {
			return null;
		}

		return response.getResponse().getPlayers().getFirst();
	}
}