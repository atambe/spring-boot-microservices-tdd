package com.training.anagramserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfiguration {
	
	@Bean
	public ErrorDecoder feignErrorDecoder(){
		return new FeignErrorDecoder();
	}
	
	@Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

}
