package com.guide.studentmanagementsystem.controller;

import com.guide.studentmanagementsystem.entity.Tasks;
import com.guide.studentmanagementsystem.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TasksController {

    @Autowired
    public TasksService tasksService;

    @PostMapping
    public Tasks create(@RequestBody Tasks tasks) {
        return tasksService.createTask(tasks);
    }

    @GetMapping
    public List<Tasks> getAllTasks(){
        return tasksService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Tasks getTaskById(@PathVariable Long id){
        return tasksService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public String taskUpdate(@RequestBody Tasks tasks, @PathVariable Long id){
        tasksService.updateTask(tasks, id);
        return "Task is updated";
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        tasksService.deleteTask(id);
    }

}
