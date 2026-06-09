package com.tracker.backend.utils;

import org.springframework.http.ResponseCookie;

public class CookieUtils {
	public static ResponseCookie createJwtCookie(String token, int duration) {
		return ResponseCookie.from("jwt", token)
				.httpOnly(true)
				.secure(false)
				.path("/")
				.maxAge(duration * 60)
				.build();
	}
}
