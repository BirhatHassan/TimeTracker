package com.tracker.backend.service.serviceinterface;

import com.tracker.backend.entity.User;
import com.tracker.backend.repository.UserRepository;

public class AuthService {
	public UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUserByEmail(String emailAdress, String password) {
		userRepository.getUserByEmailAddress(emailAdress);

		if (!userRepository.getUserByEmailAddress(emailAdress).equals(password)) {

		}

		return userRepository.getUserByEmailAddress(emailAdress);
	}
}
