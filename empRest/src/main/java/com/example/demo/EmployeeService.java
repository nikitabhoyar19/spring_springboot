package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmployeeService {

	Employee addEmployee(Employee emp);
	Employee getEmployee(Long id);
	List<Employee> getAllEmployee();
	Employee updateEmployee(Employee emp);
	void deleteEmployee(Long id);
	List<Employee> getByName(String name);
	List<Employee> getAllEmployeesByNameAndSkill(String name, String skill);
	
}
