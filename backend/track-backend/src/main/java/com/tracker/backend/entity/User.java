package com.tracker.backend.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String username;

	public String password;

	private String emailAddress;

	private Boolean isVerified;

	private String verificationToken;

	private Date verifiedAt;

	private String isOnline;

	private Date lastSeenAt;

	private Date createdAt;

	private Date updatedAt;

	private Date birthDate;

	private String phoneNumber;

	private int failedAttempts;

	private Boolean isLocked;

}
