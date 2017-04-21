package com.training.authserver.service;

import javax.inject.Inject;

import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.authserver.domain.User;
import com.training.authserver.dto.AccountUser;
import com.training.authserver.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserDetailService.class);
	
	@Inject
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 log.debug("Fetching data for " + username);
		 User u = userRepo.getUserByName(username);
		
		if (u == null){
			throw new UsernameNotFoundException(username + "  Not Found");
		}
		
		AccountUser au = new AccountUser(u.getName(), u.getPassword(), u.getRole());
		 log.debug("Returning user " + au + " from AccountUserSvc");
		return au;
	}

}
