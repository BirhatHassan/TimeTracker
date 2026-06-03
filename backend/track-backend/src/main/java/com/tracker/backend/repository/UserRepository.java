package com.tracker.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User getUserByEmailAddress(String emailAddress);

	public Boolean existsByEmailAddress(String emailAddress);

	public Boolean existsByUsername(String username);
}
