package com.training.anagramserver.auth;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.training.anagramserver.dto.UserDto;

@Service
public class BasicAuthServiceImpl implements AuthService {
	
	private static final Logger log = LoggerFactory.getLogger(BasicAuthServiceImpl.class);
	
	@Inject
	AuthServiceFeignClient authServiceFeignClient;
	
	@Override
	public boolean validateAuth(Authentication auth) {
		UserDto uDto = new UserDto(auth.getName(), auth.getCredentials().toString());
		
		log.debug("Authentication with Auth server for {" 
				+ uDto.getName() + "," + uDto.getPassword() + "}");
		try {
			authServiceFeignClient.login(uDto);
		} catch (AuthenticationServiceException ase){
			return false;
		} catch (Throwable iae){
			log.error("Encountered exception: " + iae.getClass().getName() + " with error: " + iae.getMessage());
			return false;
		}
		return true;
	}

}
