package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TaskDependency {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Taskitems dependentTask;

    @ManyToOne
    @JoinColumn(name = "dependency_id")
    private Taskitems dependency;
    
	public TaskDependency() {
		super();
	}

	public TaskDependency(Long id, Taskitems dependency, Taskitems dependentTask) {
		super();
		this.id = id;
		this.dependentTask = dependentTask;
		this.dependency = dependency;
	}

	public Long getId() {
		return id;
	}

	public Taskitems getDependentTask() {
		return dependentTask;
	}

	public void setDependentTask(Taskitems dependentTask) {
		this.dependentTask = dependentTask;
	}

	public Taskitems getDependency() {
		return dependency;
	}

	public void setDependency(Taskitems dependency) {
		this.dependency = dependency;
	}    
    
}
