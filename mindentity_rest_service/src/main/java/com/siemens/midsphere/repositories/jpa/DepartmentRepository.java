package com.siemens.midsphere.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import com.siemens.midsphere.domains.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {

}
