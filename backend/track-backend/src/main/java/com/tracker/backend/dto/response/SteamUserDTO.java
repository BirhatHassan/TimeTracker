package com.tracker.backend.dto.response;

import lombok.Data;

@Data
public class SteamUserDTO {
	private String steamid;
	private int communityvisibilitystate;
	private int profilestate;
	private String personaname;
	private String profileurl;
	private String avatar;
	private String avatarmedium;
	private String avatarfull;
	private String avatarhash;
	private long lastlogoff;
	private int personastate;
	private String primaryclanid;
	private long timecreated;
	private int personastateflags;
}