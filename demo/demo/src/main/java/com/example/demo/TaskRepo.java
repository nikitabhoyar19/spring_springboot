package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Taskitems, Long>{
	
	//public TaskItem  findTasksByPriority(int id);
	
	//public TaskItem findTasksByDueDate(LocalDateTime duedate);
	
	public List<Taskitems> findByStatus(String status);
	public List<Taskitems> findByDuedate(LocalDate duedate);
	public List<Taskitems> findByPriority(String priority);
	public List<Taskitems> findByCategory(String category);
	
	// find by any attribute
	public List<Taskitems> findByDescriptionIgnoreCaseOrStatusIgnoreCaseOrPriorityIgnoreCaseOrCategoryIgnoreCase(String description, 
			String status, String priority, String category);

}
