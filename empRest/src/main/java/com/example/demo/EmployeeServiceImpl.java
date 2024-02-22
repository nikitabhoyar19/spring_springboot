package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmpDao dao;

	@Override
	public Employee addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return dao.save(emp);
	}

	@Override
	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return dao.save(emp);
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<Employee> getByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

	@Override
	public List<Employee> getAllEmployeesByNameAndSkill(String name, String skill) {
		// TODO Auto-generated method stub

//		through custom queries
//		return dao.findEmployeesByNameAndSkillIndexJPQL(name, skill);
//		return dao.findEmployeesByNameAndSkillIndexJPQL1(name, skill);
//		return dao.findEmployeesByNameAndSkillIndexNative(name, skill);
//		return dao.findEmployeesByNameAndSkillIndexNative1(name, skill);
			
		return dao.findByNameAndSkill(name, skill);
	}

}
