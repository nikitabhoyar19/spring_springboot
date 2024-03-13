package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TaskService {
	
	@Autowired
	TaskRepo taskrepo;
	
	@Autowired
	TaskDependencyRepo taskdependencyrepo;
	
	public Optional<Taskitems> getAll(Long id) {
		return taskrepo.findById(id);		
	}
	
	public Taskitems saveTask(Taskitems taskItem) {
		System.out.print(taskItem.toString());
		return taskrepo.save(taskItem);
	}
	
	public TaskDependency saveTaskDependency(TaskDependency taskdependency) {
		System.out.print(taskdependency.toString());
		return taskdependencyrepo.save(taskdependency);
	}
	
	public List<Taskitems> saveTasks(List<Taskitems> tasks) {
		return taskrepo.saveAll(tasks);
	}
	
	public List<Taskitems> getAllTasks() {
		return taskrepo.findAll();
	}
	
	public List<Taskitems>  getTasksByPriority(String priority) {
		return taskrepo.findByPriority(priority);
	}
	
	public List<Taskitems>  getTasksByCategory(String category) {
		return taskrepo.findByCategory(category);
	}
	
	public List<Taskitems> getTasksByStatus(String status) {
		return taskrepo.findByStatus(status);
	}
	
	public List<Taskitems> getTasksByDueDate(LocalDate duedate) {
		return taskrepo.findByDuedate(duedate);
	}
	
	public Taskitems updateTask(Taskitems taskitems) {
		System.out.println("update");
		Taskitems existing_task = taskrepo.findById(taskitems.getId()).orElse(null);
		existing_task.setDescription(taskitems.getDescription());
		existing_task.setCategory(taskitems.getCategory());
		existing_task.setDuedate(taskitems.getDuedate());
		existing_task.setPriority(taskitems.getPriority());
		existing_task.setStatus(taskitems.getStatus());
		
		return taskrepo.save(existing_task);
	}
	
	public Taskitems updateStatusToComplete(Long id) throws TaskNotFoundException {
	    Optional<Taskitems> optionalTask = taskrepo.findById(id);
	    if (optionalTask.isPresent()) {
	        Taskitems existing_task = optionalTask.get();
	        existing_task.setStatus("Completed");
	        return taskrepo.save(existing_task);
	    } else {
	        throw new TaskNotFoundException("Task with id not found");
	    }
	}  
	
	public Taskitems updateStatusToCompleted(Long id) {
		Taskitems existing_task = taskrepo.findById(id).orElseThrow();
	    existing_task.setStatus("Completed");
	    return taskrepo.save(existing_task);
	}
	
	public List<Taskitems> filterTasks(String status, LocalDate duedate, String priority, String category) {
		
		List<Taskitems> filteredTasks = null;
		
		if(status != null && !status.isEmpty()) {
			filteredTasks = taskrepo.findByStatus(status);
		}
		
		if(duedate != null) {
			filteredTasks = taskrepo.findByDuedate(duedate);
	
		}
		
		if(category != null && !category.isEmpty()) {
			filteredTasks = taskrepo.findByCategory(category);
	
		}
		
		if(priority != null && !priority.isEmpty()) {
			filteredTasks = taskrepo.findByPriority(priority);
		}
		
		return filteredTasks;
		
	}
	
	//Bonus 
//	@PutMapping("/{taskId}")
//    public ResponseEntity<Taskitems> updateTask(@PathVariable Long taskId, @RequestBody Taskitems updatedTask) {
//        Taskitems existingTask = taskrepo.findById(taskId).orElse(null);
//        if (existingTask == null) {
//            return ResponseEntity.notFound().build();
//        }
//        existingTask.setDescription(updatedTask.getDescription());
//       
//        existingTask.setDependencies(updatedTask.getDependencies());
//        
//        // Save the updated task
//        Taskitems savedTask = taskrepo.save(existingTask);
//        return ResponseEntity.ok(savedTask);
//    }
//
//    @PutMapping("/{taskId}/complete")
//    public ResponseEntity<Taskitems> markTaskAsCompleted(@PathVariable Long taskId) {
//        Taskitems task = taskrepo.findById(taskId).orElse(null);
//        if (task == null) {
//            return ResponseEntity.notFound().build();
//        }
//        
//        // Check if all dependencies are completed
//        for (TaskDependency dependency : task.getDependencies()) {
//            if (!dependency.getDependency().getStatus().equals("Completed")) {
//                return null;
//            }
//        }
//        
//        // All dependencies are completed, mark task as completed
//        task.setStatus("Completed");
//        Taskitems savedTask = taskrepo.save(task);
//        return ResponseEntity.ok(savedTask);
//    }
	

}
