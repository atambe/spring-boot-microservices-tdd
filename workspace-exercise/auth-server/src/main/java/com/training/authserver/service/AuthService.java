package com.training.authserver.service;

import java.util.List;

import com.training.authserver.domain.User;

public interface AuthService {	
	public List<User> listUsers();
	public User addUser(User user);
	public void deleteUser(String name);
	public boolean isValidCredentials(String userName, String password);
}
