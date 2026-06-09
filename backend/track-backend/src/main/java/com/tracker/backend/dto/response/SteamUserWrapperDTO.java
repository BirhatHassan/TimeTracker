package com.tracker.backend.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class SteamUserWrapperDTO {

	private Response response;

	@Data
	public static class Response {
		private List<SteamUserDTO> players;
	}
}