package com.siemens.midsphere.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import com.siemens.midsphere.domains.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

}
