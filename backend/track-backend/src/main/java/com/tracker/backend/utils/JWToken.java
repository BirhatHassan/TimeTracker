package com.tracker.backend.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWToken {
	private static final String CLAIM_CALLNAME = "callName";
	private static final String CLAIM_USERID = "id";
	private static final String CLAIM_USERNAME = "userName";

	private final Long userId;
	private final String callName;
	private final String userName;

	public JWToken(String callName, Long userId, String userName) {
		this.callName = callName;
		this.userId = userId;
		this.userName = userName;
	}

	public String encode(String issuer, String passphrase, int expirationMinutes) {
		SecretKey key = getKey(passphrase);

		return Jwts.builder()
				.claim(CLAIM_CALLNAME, this.callName)
				.claim(CLAIM_USERID, this.userId)
				.claim(CLAIM_USERNAME, this.userName)
				.issuer(issuer)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expirationMinutes * 60L * 1000L))
				.signWith(key) // Modern JJWT automatically detects the algorithm from the SecretKey
				.compact();
	}

	public static JWToken verifyToken(String token, String passphrase) {
		try {
			Claims claims = Jwts.parser()
					.verifyWith(getKey(passphrase))
					.build()
					.parseSignedClaims(token)
					.getPayload();

			return new JWToken(
					claims.get(CLAIM_CALLNAME, String.class),
					claims.get(CLAIM_USERID, Long.class),
					claims.get(CLAIM_USERNAME, String.class)
			);

		} catch (JwtException | IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
		}
	}

	private static SecretKey getKey(String passphrase) {
		return Keys.hmacShaKeyFor(passphrase.getBytes(StandardCharsets.UTF_8));
	}

	public Long getUserId() {
		return userId;
	}

	public String getCallName() {
		return callName;
	}

	public String getUserName() {
		return userName;
	}
}