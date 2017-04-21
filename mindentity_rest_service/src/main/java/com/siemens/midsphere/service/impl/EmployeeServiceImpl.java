package com.siemens.midsphere.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siemens.midsphere.domains.Employee;
import com.siemens.midsphere.domains.Project;
import com.siemens.midsphere.domains.Role;
import com.siemens.midsphere.repositories.jpa.EmployeeRepository;
import com.siemens.midsphere.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Override
	public Employee createEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return repository.save(employee);
	}

	@Override
	public Employee addProject(String empId, Project project) {
		Employee employee = repository.findOne(empId);
		employee.setProjects(project);
		return repository.save(employee);
	}

	@Override
	public Employee assignRole(String empId, Role role) {
		// TODO Auto-generated method stub
		Employee employee = repository.findOne(empId);
		employee.setRoles(role);
		return repository.save(employee);
	}

	@Override
	public Employee getEmployeeById(String empId) {
		// TODO Auto-generated method stub
		return repository.findOne(empId);
	}

}
