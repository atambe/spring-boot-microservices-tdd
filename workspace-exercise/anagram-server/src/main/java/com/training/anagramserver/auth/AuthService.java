package com.training.anagramserver.auth;

import org.springframework.security.core.Authentication;

public interface AuthService {

	public boolean validateAuth(Authentication auth);
	
}
