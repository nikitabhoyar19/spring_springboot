package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	TaskService taskservice;
	
	@Autowired
	TaskRepo taskrepo;
	
	
	@GetMapping
	public List<Taskitems> getAllTasks() {
		return taskservice.getAllTasks();
	}
	
	@PostMapping
	public ResponseEntity<Taskitems> createTasks(@RequestBody Taskitems task) {
		Taskitems createTask = taskservice.saveTask(task);
		return new ResponseEntity<>(createTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public Optional<Taskitems> getDataById(@PathVariable Long id) {
		return taskservice.getAll(id);
	}
	
	@GetMapping("status/{status}")
	public List<Taskitems> getDataByStatus(@PathVariable String status) {
		return taskservice.getTasksByStatus(status);
	}
	
	@PutMapping("/{id}/complete")
	public ResponseEntity<Taskitems> completeTask(@PathVariable Long id) throws TaskNotFoundException {
	Taskitems updatedTask = taskservice.updateStatusToComplete(id);
	if (updatedTask != null) {
		return ResponseEntity.ok(updatedTask);
	} 
	else {
		return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/filter") 
	public List<Taskitems> filterTasks(
			@RequestParam(required = false) String status,
			@RequestParam(required = false) LocalDate duedate,
			@RequestParam(required = false) String priority,
			@RequestParam(required = false) String category
			) 
	{
		return taskservice.filterTasks(status, duedate, priority, category);
	}
	
	@PostMapping("/addDependencies")
	public ResponseEntity<TaskDependency> createDependency(@RequestBody TaskDependency taskdependency) {
		TaskDependency taskdependency1 = taskservice.saveTaskDependency(taskdependency);
		return new ResponseEntity<>(taskdependency1, HttpStatus.CREATED);
	}
	
	//Bonus 
	@PutMapping("/{taskId}")
    public ResponseEntity<Taskitems> updateTask(@PathVariable Long taskId, @RequestBody Taskitems updatedTask) {
        Taskitems existingTask = taskrepo.findById(taskId).orElse(null);
        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }
        existingTask.setDescription(updatedTask.getDescription());
       
        existingTask.setDependencies(updatedTask.getDependencies());
        
        // Save the updated task
        Taskitems savedTask = taskrepo.save(existingTask);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{taskId}/completeit")
    public ResponseEntity<Taskitems> markTaskAsCompleted(@PathVariable Long taskId) {
        Taskitems task = taskrepo.findById(taskId).orElse(null);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Check if all dependencies are completed
        for (TaskDependency dependency : task.getDependencies()) {
            if (!dependency.getDependency().getStatus().equals("Completed")) {
                return ResponseEntity.status(500).build();
            }
        }
        
        // All dependencies are completed, mark task as completed
        task.setStatus("Completed");
        Taskitems savedTask = taskrepo.save(task);
        return ResponseEntity.ok(savedTask);
    }

}
