package com.training.authserver.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.training.authserver.domain.User;
import com.training.authserver.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Inject
	UserRepository userRepo;

	@Override
	public List<User> listUsers() {
		log.debug(getCommandActor() + " is listing users"); 
		return userRepo.findAll();
	}

	@Override
	public User addUser(User user) {
		log.debug(getCommandActor() + " is saving user: " + user.getName());
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(String name) {
		log.debug(getCommandActor() + " is deleting user: " + name);
		userRepo.deleteUserByName(name);
	}

	@Override
	public boolean isValidCredentials(String userName, String password) {
		User fetchedUser = userRepo.getUserByName(userName);
		if (fetchedUser == null || ! fetchedUser.getPassword().equals(password)){
			log.error("Authentication Check failed for user: " + userName);
			return false;
		}
		return true;
	}
	
	private String getCommandActor(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
