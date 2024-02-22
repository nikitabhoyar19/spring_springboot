package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmpDao extends CrudRepository<Employee, Long>{
	
	 List<Employee> findAll();
	 List<Employee> findByName(String name);
	 List<Employee> findByNameAndSkill(String name, String skill);
	 
	 /////////////////////////////////////////////////////////////////
	 
	 // Simple Query 
	 
//	 @Query("select e from Employee")
//	 List<Employee> findAll();
	 
	 ////////////////////////////////////////////////////////////////
	 
	 // Custom Queries
	 
	 //1. Using JPQL
	 @Query("SELECT e FROM Employee e where e.name = ?1 and e.skill = ?2")
	 List<Employee> findEmployeesByNameAndSkillIndexJPQL1(String name, String skill);
	 
	 @Query("SELECT e FROM Employee e where e.name = :name1 and e.skill = :skill1")
	 List<Employee> findEmployeesByNameAndSkillIndexJPQL(@Param("name1") String name, @Param("skill1") String skill);
	 
	 
	 //2. Using Native Query
	 @Query(value = "SELECT * FROM Employee WHERE name = ?1 and skill = ?2", nativeQuery = true)
	 List<Employee> findEmployeesByNameAndSkillIndexNative(String name, String skill);
	 
	 @Query(value = "SELECT * FROM Employee WHERE name = :name and skill = :skill", nativeQuery = true)
	 List<Employee> findEmployeesByNameAndSkillIndexNative1(@Param("name") String name, @Param("skill") String skill);
	 
}
