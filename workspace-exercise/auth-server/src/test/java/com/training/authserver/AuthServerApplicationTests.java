package com.training.authserver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.contains;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.authserver.domain.Roles;
import com.training.authserver.dto.UserDto;
import com.training.authserver.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class AuthServerApplicationTests {

	@Inject
	private MockMvc mockMvc;
	
	@Inject
    ObjectMapper objectMapper;
	
	@Inject
	UserRepository userRepo;

	@Test
	public void test_listUsersWithAdminRole() throws Exception {
	 mockMvc.perform(get ("/admin/users").with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[*].name", hasItems("admin", "tuser1")));
	}
	
	@Test
	public void test_listUsersWithUserRole() throws Exception {
	 mockMvc.perform(get ("/admin/users").with(user("user").password("user").roles(Roles.ROLE_USER.getName())))
	 	.andExpect(status().isForbidden());
	}
	
	@Test
	public void test_addUserWithAdminRole() throws Exception {
		String uName = "testUser-Add";
		UserDto uDto = new UserDto(uName, uName + "-pass", Roles.ROLE_USER.getCanonicalName());
		mockMvc.perform(
			 post ("/admin/users")
			 .with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))
			 .content(objectMapper.writeValueAsBytes(uDto))
			 .contentType(MediaType.APPLICATION_JSON_UTF8)
			 )
	    .andExpect(status().isCreated());
	 
		mockMvc.perform(get ("/admin/users").with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.length()").value(3))
		.andExpect(jsonPath("$[*].name", hasItems("admin", "tuser1",uName)));
		
		// delete that newly added user
		userRepo.deleteUserByName(uName);
	}
	
	@Test
	public void test_addUserWithAdminRoleAndNullPassword() throws Exception {
		String uName = "testUser-Add";
		UserDto uDto = new UserDto(uName, null, Roles.ROLE_USER.getCanonicalName());
		mockMvc.perform(
			 post ("/admin/users")
			 .with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))
			 .content(objectMapper.writeValueAsBytes(uDto))
			 .contentType(MediaType.APPLICATION_JSON_UTF8)
			 )
	    .andExpect(status().isBadRequest());	 
	}
	
	@Test
	public void test_addUserWithAdminRoleAndInvalidRole() throws Exception {
		String uName = "testUser-Add";
		UserDto uDto = new UserDto(uName, uName + "-pass", "MASTER");
		mockMvc.perform(
			 post ("/admin/users")
			 .with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))
			 .content(objectMapper.writeValueAsBytes(uDto))
			 .contentType(MediaType.APPLICATION_JSON_UTF8)
			 )
	    .andExpect(status().isBadRequest());	 
	}
	
	@Test
	public void test_delUserWithAdminRole() throws Exception {
		String uName = "testUser-Del";
		UserDto uDto = new UserDto(uName, uName + "-pass", Roles.ROLE_USER.getCanonicalName());
		
		// Create user
		mockMvc.perform(
			 post ("/admin/users")
			 .with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))
			 .content(objectMapper.writeValueAsBytes(uDto))
			 .contentType(MediaType.APPLICATION_JSON_UTF8)
			 )
	    .andExpect(status().isCreated());
	 
		// Test user
		mockMvc.perform(get ("/admin/users").with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.length()").value(3))
		.andExpect(jsonPath("$[*].name", hasItems("admin", "tuser1",uName)));
		
		// Delete user 
		mockMvc.perform(
				 delete ("/admin/users/" + uName)
				 .with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))
				 )
		    .andExpect(status().isNoContent());		
	}
	
	@Test
	public void test_delUserWithUserRole() throws Exception {
		String uName = "testUser-Del";
		UserDto uDto = new UserDto(uName, uName + "-pass", Roles.ROLE_USER.getCanonicalName());
		
		// Create user
		mockMvc.perform(
			 post ("/admin/users")
			 .with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))
			 .content(objectMapper.writeValueAsBytes(uDto))
			 .contentType(MediaType.APPLICATION_JSON_UTF8)
			 )
	    .andExpect(status().isCreated());
	 
		// Test user
		mockMvc.perform(get ("/admin/users").with(user("admin").password("admin").roles(Roles.ROLE_ADMIN.getName()))).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.length()").value(3))
		.andExpect(jsonPath("$[*].name", hasItems("admin", "tuser1",uName)));
		
		// Delete user 
		mockMvc.perform(
				 delete ("/admin/users/" + uName)
				 .with(user("user").password("user").roles(Roles.ROLE_USER.getName()))
				 )
		    .andExpect(status().isForbidden());
		
		userRepo.deleteUserByName(uName);
	}
	

	@Test
	public void test_addUserWithUserRole() throws Exception {
		UserDto uDto = new UserDto("testUser1", "testUser1-pass", Roles.ROLE_USER.getCanonicalName());
		mockMvc.perform(
			 post ("/admin/users")
			 .with(user("user").password("user").roles(Roles.ROLE_USER.getName()))
			 .content(objectMapper.writeValueAsBytes(uDto))
			 .contentType(MediaType.APPLICATION_JSON_UTF8)
			 )
	    .andExpect(status().isForbidden());
	}
	
	@Test
	public void test_loginValidUser() throws Exception {
		UserDto uDto = new UserDto("admin", "admin","");
		mockMvc.perform(
			 post ("/login")
			 .content(objectMapper.writeValueAsBytes(uDto))
			 .contentType(MediaType.APPLICATION_JSON_UTF8)
			 )
	    .andExpect(status().isOk());
	}
	
	@Test
	public void test_loginInvalidUser() throws Exception {
		UserDto uDto = new UserDto("admin", "admin123","");
		mockMvc.perform(
			 post ("/login")
			 .content(objectMapper.writeValueAsBytes(uDto))
			 .contentType(MediaType.APPLICATION_JSON_UTF8)
			 )
	    .andExpect(status().isUnauthorized());
	}
	
}
