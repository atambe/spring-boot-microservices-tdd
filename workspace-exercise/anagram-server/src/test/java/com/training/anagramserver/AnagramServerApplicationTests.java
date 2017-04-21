package com.training.anagramserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Base64Utils;

import com.training.anagramserver.auth.AuthService;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class AnagramServerApplicationTests {

	@MockBean
	AuthService authService;
	
	@Inject
	MockMvc mockMvc;
		
	@Test
	public void test_checkInvalidCreds() throws Exception {
		when(authService.validateAuth(any(Authentication.class))).thenReturn(false);
		
		mockMvc.perform(get ("/anagrams?word=test")
				.header(HttpHeaders.AUTHORIZATION,
	                    "Basic " + Base64Utils.encodeToString("user:pass".getBytes())))
	 	.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void test_checkValidCreds() throws Exception {
		when(authService.validateAuth(any(Authentication.class))).thenReturn(true);
		
		mockMvc.perform(get ("/anagrams?word=marshman")
				.header(HttpHeaders.AUTHORIZATION,
	                    "Basic " + Base64Utils.encodeToString("user:pass".getBytes())))
	 	.andExpect(status().isOk());
	}
	
	@Test
	public void test_checkInvalidRequest() throws Exception {
		when(authService.validateAuth(any(Authentication.class))).thenReturn(true);
		
		ResultActions result = mockMvc.perform(get ("/anagrams")
				.header(HttpHeaders.AUTHORIZATION,
	                    "Basic " + Base64Utils.encodeToString("user:pass".getBytes())))
	 	.andExpect(status().isBadRequest());
	}
	
	@Test
	public void test_checkValidAnagrams() throws Exception {
		when(authService.validateAuth(any(Authentication.class))).thenReturn(true);
		
		mockMvc.perform(get ("/anagrams?word=nard")
				.header(HttpHeaders.AUTHORIZATION,
	                    "Basic " + Base64Utils.encodeToString("user:pass".getBytes())))
	 	.andExpect(status().isOk())
	 	.andExpect((jsonPath("$", Matchers.containsInAnyOrder("darn", "rand", "nard"))));
	}

	@Test
	public void test_checkNoAnagram() throws Exception {
		when(authService.validateAuth(any(Authentication.class))).thenReturn(true);
		
		ResultActions result = mockMvc.perform(get ("/anagrams?word=abcd")
				.header(HttpHeaders.AUTHORIZATION,
	                    "Basic " + Base64Utils.encodeToString("user:pass".getBytes())))
	 	.andExpect(status().isOk());
		
		result.andExpect(content().string("[]"));
	}
	
}
