package com.training.authserver.domain;

public enum Roles {
  ROLE_ADMIN("ADMIN"), ROLE_USER("USER");
	
	private String roleName;
	
	public String getCanonicalName(){
		return "ROLE_" + roleName;
	}
	
	public String getName(){
		return roleName;
	}
	
	Roles(String name){
		this.roleName = name;
	}
}
