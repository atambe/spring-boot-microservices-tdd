package com.training.anagramserver.dto;

import lombok.Data;


@Data
public class UserDto {
	String name;	
	String password;
	
	public UserDto(){
	}
	
	public UserDto (String name, String pass){
		this.name = name;
		this.password = pass;
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
	
	
}
