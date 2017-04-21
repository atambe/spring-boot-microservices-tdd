package com.training.anagramserver;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.training.anagramserver.config.RestBasedAuthProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	RestBasedAuthProvider restBasedAuthProvider;

	private static final Logger log = Logger.getLogger(SecurityConfig.class);

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(false);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		log.debug(">>>>>>>>>>>>>> Setting security config");

		http
		.authorizeRequests()
		.anyRequest().authenticated().and().httpBasic();

		http.csrf().disable();
	}

	@Override
	protected void configure(
			AuthenticationManagerBuilder auth) throws Exception {
		log.debug(">>>>>>>>>>>>>> Setting Auth Provider");
		auth.authenticationProvider(restBasedAuthProvider);
	}

}
