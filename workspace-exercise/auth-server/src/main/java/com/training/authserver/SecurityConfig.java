package com.training.authserver;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.training.authserver.service.UserDetailService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger log = Logger.getLogger(SecurityConfig.class);
	
	@Inject
	UserDetailService accountUserDetailService;
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.debug(false);
	    }
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	  
		 log.debug("Setting security config");
		 
	      http
	      .authorizeRequests()
	         .antMatchers("/login").permitAll()
	         .anyRequest().authenticated().and().httpBasic();
	      
	      http.csrf().disable();
	      
	      //http.headers().frameOptions().disable();
	      //authorizeRequests().antMatchers("/console/**").permitAll().anyRequest().authenticated().and().httpBasic();
	        
	    }
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth)
	   throws Exception {		 
	     auth.userDetailsService(accountUserDetailService);
	 }
	 
}
