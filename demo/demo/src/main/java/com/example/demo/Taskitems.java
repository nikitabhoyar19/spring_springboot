package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Taskitems{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	private String status;
	private String priority;
	private String category;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate duedate;
	
	@OneToMany(mappedBy = "dependentTask")
    @JsonIgnore
    private List<TaskDependency> dependencies;
	
	public Taskitems() {
		super();
	}
	
	public Taskitems(Long id, String description, String status, String priority, String category, LocalDate duedate,
			List<TaskDependency> dependencies) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.category = category;
		this.duedate = duedate;
		this.dependencies = dependencies;
	}

	public List<TaskDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<TaskDependency> dependencies) {
		this.dependencies = dependencies;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDuedate() {
		return duedate;
	}
	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	

}
