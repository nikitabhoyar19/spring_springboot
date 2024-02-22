package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Employee REST API")
public class EmployeController {
	
	@Autowired
	private EmployeeService eservice;

	@PostMapping("/empp")
	@Operation(summary = "Adds an employee", description = "Takes an employee object")
	public @ApiResponse(description = "Returns an emp object")Employee addEmployee(@Parameter(description = "Employee object")@RequestBody Employee emp) {
		
		return eservice.addEmployee(emp);
	}
	
	@GetMapping("/empp/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee getEmployee(@PathVariable Long id) {
		return eservice.getEmployee(id);
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> sayHello(){
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}
	
	@GetMapping("/empp")
	@Operation(summary = "Get all employee", description = "show employees")
	public List<Employee> getAllEmployee() {
			
		return eservice.getAllEmployee();
	}
	
	@PutMapping("/empp")
	public Employee updateEmployee(@RequestBody Employee emp) {
		
		return eservice.updateEmployee(emp);
	}
	
	@DeleteMapping("/empp/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		
		eservice.deleteEmployee(id);
		System.out.println("Employee Deleted with id" + id);
	}
	
	@GetMapping("/empp/empname/{name}")
	public List<Employee> getByName(@PathVariable String name) {
		
		return eservice.getByName(name);
	}
	
	// On Browser http://localhost:8080/empp/empnames/nikita?skill=java
	// you can use @PathVariable for both
	@GetMapping("empp/empnames/{name}")
	public List<Employee> getAllEmployeesByNameAndSkill(@PathVariable String name, @RequestParam String skill) {
		return eservice.getAllEmployeesByNameAndSkill(name, skill);
	}
	
}
