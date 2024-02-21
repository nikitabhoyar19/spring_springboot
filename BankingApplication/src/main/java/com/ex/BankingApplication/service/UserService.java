package com.ex.BankingApplication.service;

import java.util.List;
import java.util.Optional;

import com.ex.BankingApplication.entity.User;

public interface UserService {
	
	User createUser(User user);
	List<User> getUserDetails();
	Optional<User> getUserById(Long userId);

}
