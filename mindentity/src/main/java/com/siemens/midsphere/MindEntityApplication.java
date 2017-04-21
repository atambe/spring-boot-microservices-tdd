package com.siemens.midsphere;


import java.sql.Date;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.siemens.midsphere.domains.Address;
import com.siemens.midsphere.domains.Department;
import com.siemens.midsphere.domains.Employee;
import com.siemens.midsphere.domains.Project;
import com.siemens.midsphere.domains.Role;
import com.siemens.midsphere.repositories.jpa.AddressRepository;
import com.siemens.midsphere.repositories.jpa.DepartmentRepository;
import com.siemens.midsphere.repositories.jpa.RoleRepository;
import com.siemens.midsphere.service.EmployeeService;
import com.siemens.midsphere.service.ProjectService;

@SpringBootApplication
public class MindEntityApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MindEntityApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MindEntityApplication.class);
	}
	
	
	@Bean
	public CommandLineRunner demo(EmployeeService employeeService, ProjectService projectService, RoleRepository roleRepository,
			DepartmentRepository deptRepository, AddressRepository addressRepository){
		
		return (args) -> {
		Address address = new Address();
		address.setCity("Bhopal");
		address.setCountry("Inida");
		address.setState("MP");
		address.setStreet("Nehrunagar");
		address = addressRepository.save(address);
		
		Department department = new Department();
		department.setDeptName("R & D");
		department.setAddress(address);
		department = deptRepository.save(department);
		
		Role role = new Role();
		role.setRoleName("techlead");
		role = roleRepository.save(role);
		
		Project project =  new Project();
		project.setName("System API");
		project.setFromDate(new Date(Calendar.getInstance().getTime().getTime()));
		project.setToDate(new Date(Calendar.getInstance().getTime().getTime()));
		project = projectService.createProject(project);
		
		Employee employee = new Employee();
		employee.setAddress(address);
		employee.setDepartment(department);
		employee.setDob(new Date(Calendar.getInstance().getTime().getTime()));
		employee.setFirstName("Stuti");
		employee.setLastName("jain");
		employee.setLoginName("jstuti");
		employee.setRoles(role);
		employee.setProjects(project);
		employee = employeeService.createEmployee(employee);
		
		
		log.info(employee.toString());
		
		
		};
	}
}
