package com.training.anagramserver.config;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.training.anagramserver.auth.AuthService;

@Component
public class RestBasedAuthProvider implements AuthenticationProvider {
	
	private static final Logger logger =     LoggerFactory.getLogger(RestBasedAuthProvider.class);

	@Inject
	AuthService authService;
	
	public RestBasedAuthProvider() {
	    logger.info("*** RestBasedAuthProvider created");
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.debug("Authenticating user: " + authentication.getName());
		if (authService.validateAuth(authentication)){
			return new UsernamePasswordAuthenticationToken(
		              authentication.getName()
		              , authentication.getCredentials().toString(), new ArrayList<>());
		}
		throw new BadCredentialsException("Please try with valid credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
