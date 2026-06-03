package com.tracker.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.backend.dto.response.LoginResponseDTO;
import com.tracker.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public LoginResponseDTO getUserByEmailAddress(String emailAdress);
}
