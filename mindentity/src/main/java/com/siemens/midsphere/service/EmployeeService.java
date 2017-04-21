package com.siemens.midsphere.service;

import org.springframework.stereotype.Service;

import com.siemens.midsphere.domains.Employee;
import com.siemens.midsphere.domains.Project;
import com.siemens.midsphere.domains.Role;



public interface EmployeeService {

	Employee createEmployee(Employee employee);
	
	Employee updateEmployee(Employee employee);
	
	Employee addProject (String empId, Project project);
	
	Employee assignRole (String empId, Role role);
	
	Employee getEmployeeById(String empId);
	
	
}
