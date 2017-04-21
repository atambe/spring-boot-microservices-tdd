package com.training.anagramserver.auth;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.anagramserver.config.FeignConfiguration;
import com.training.anagramserver.dto.UserDto;

import feign.Headers;

//@FeignClient(name = "auth-service", url = "http://localhost:2222", configuration = FeignConfiguration.class)
@FeignClient(name = "http://auth-service", configuration = FeignConfiguration.class)
public interface AuthServiceFeignClient {
	@Headers("Content-Type: application/json")
	@RequestMapping(method = RequestMethod.POST, path =  "/login")
    String login(UserDto uDto);
}
