package com.siemens.midsphere.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.siemens.midsphere.domains.Role;
import com.siemens.midsphere.repositories.jpa.RoleRepository;
import com.siemens.midsphere.service.RoleService;

public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository repository;

	@Override
	public Role createRole(Role role) {
		// TODO Auto-generated method stub
		return repository.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return repository.save(role);
	}

	@Override
	public Role getByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return repository.findOne(roleId);
		
	}

}
