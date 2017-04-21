package com.siemens.midsphere.service;

import org.springframework.stereotype.Service;

import com.siemens.midsphere.domains.Role;

public interface RoleService {
	
	Role createRole(Role role);
	
	Role updateRole(Role role);
	
	Role getByRoleId(String roleId);

}
