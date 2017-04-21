package com.training.authserver.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class UserDto {
	@NotNull
    @Size(min = 3, max = 80, message = "User name is too short")
	String name;
	
	@NotNull
    @Size(min = 3, max = 80, message = "Password is too short")
	String password;
	
	String role;
	
	public UserDto(){
		
	}
	
	public UserDto (String name, String pass, String role){
		this.name = name;
		this.password = pass;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
