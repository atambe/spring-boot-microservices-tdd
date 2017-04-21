package com.training.authserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;


@Entity
@Table(name="User",  uniqueConstraints={
		   @UniqueConstraint(columnNames={"name"})
		})
@Data
public class User {
	String name;
	String password;
	String role;
	@GeneratedValue
	@Id
	Integer userId;
	
	public User(){
		
	}
	
	public User (String name, String pass, String role){
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
