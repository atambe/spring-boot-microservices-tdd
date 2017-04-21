package com.siemens.midsphere.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import com.siemens.midsphere.domains.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
