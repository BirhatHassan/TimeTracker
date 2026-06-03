package com.tracker.backend.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final String passphrase;

	// Pass the passphrase via constructor from your security configuration
	public JwtAuthenticationFilter(String passphrase) {
		this.passphrase = passphrase;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		// 1. Extract the Authorization header
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		// 2. Check if it's a valid Bearer token
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// 3. Isolate the actual token string
		String token = authHeader.substring(7);

		try {
			// 4. Use your JWToken utility to verify it
			JWToken decryptedToken = JWToken.verifyToken(token, passphrase);

			// 5. Create Spring Security Authentication object
			// Using principal: username, credentials: null, authorities: empty list (or roles if you add them later)
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					decryptedToken.getUserName(),
					null,
					Collections.emptyList()
			);

			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			// 6. Set the user in the Security Context
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (ResponseStatusException e) {
			// If token is invalid or expired, clear context and send 401 Unauthorized
			SecurityContextHolder.clearContext();
			response.sendError(e.getStatusCode().value(), e.getReason());
			return;
		}

		// Move to the next filter in the chain
		filterChain.doFilter(request, response);
	}
}