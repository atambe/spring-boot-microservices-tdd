package com.training.anagramserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

	private static final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);
	
	@Override
	public Exception decode(String methodKey, Response response) {
		log.error("Unable to authenticate: " + response.body().toString());
		if (response.status() == 401){
			throw new AuthenticationServiceException("");
		}
		throw new IllegalArgumentException();
	}

}
