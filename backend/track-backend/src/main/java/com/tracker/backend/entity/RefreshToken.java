package com.tracker.backend.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class represents the refresh token, a User could choose to login and get a refresh token.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String token;

	private Date expiryDate;
}
