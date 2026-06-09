package com.tracker.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTConfig {
	@Value("${jwt.issuer}")
	private String issuer;

	@Value("${jwt.secret}")
	private String passphrase;

	@Value("${jwt.expiration}")
	private int tokenDurationOfValidity;

	public String getIssuer() {
		return issuer;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public int getTokenDurationOfValidity() {
		return tokenDurationOfValidity;
	}
}
