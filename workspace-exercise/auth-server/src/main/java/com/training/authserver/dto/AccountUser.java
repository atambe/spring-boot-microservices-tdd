package com.training.authserver.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8357330243411977326L;
	private String userName;
	private String password;
	private List<GrantedAuthority> grantedRoles;
	
	public AccountUser(String name, String pass, String role){
		this.userName = name;
		this.password = pass;
		this.grantedRoles = new ArrayList<GrantedAuthority>(){
			{
				add(new SimpleGrantedAuthority(role));
			}
		};
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedRoles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String toString(){
		return "[name: " + userName + ", pass: " + password + ", role: " + grantedRoles.get(0) + "]";
	}

}
