package com.tracker.backend.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String COOKIE_NAME = "jwt"; // Change if needed

	private final String passphrase;

	public JwtAuthenticationFilter(String passphrase) {
		this.passphrase = passphrase;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
	) throws ServletException, IOException {

		try {
			String token = extractToken(request);

			if (token != null) {
				JWToken decryptedToken = JWToken.verifyToken(token, passphrase);

				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(
								decryptedToken.getUserName(),
								null,
								Collections.emptyList()
						);

				authentication.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
				);

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

			filterChain.doFilter(request, response);

		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			response.sendError(
					HttpServletResponse.SC_UNAUTHORIZED,
					"Invalid or expired token"
			);
		}
	}

	private String extractToken(HttpServletRequest request) {

		// 1. Authorization header
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		// 2. HTTP-only cookie
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (COOKIE_NAME.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}

		return null;
	}
}