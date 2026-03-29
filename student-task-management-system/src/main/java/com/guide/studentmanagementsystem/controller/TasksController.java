package com.guide.studentmanagementsystem.controller;

import com.guide.studentmanagementsystem.entity.TaskRequestDTO;
import com.guide.studentmanagementsystem.entity.TaskResponseDTO;
import com.guide.studentmanagementsystem.entity.Tasks;
import com.guide.studentmanagementsystem.service.TasksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TasksController {

    @Autowired
    public TasksService tasksService;

//    @PostMapping
//    public Tasks create(@RequestBody Tasks tasks) {
//        return tasksService.createTask(tasks);
//    }

//    @PostMapping
//    public TaskResponseDTO create(@RequestBody TaskRequestDTO dto) {
//        Tasks task = tasksService.convertToEntity(dto);
//        Tasks saved = tasksService.createTask(task);
//        return tasksService.convertToResponse(saved);
//    }

    @PostMapping
    public TaskResponseDTO create(@Valid @RequestBody TaskRequestDTO dto) {
        return tasksService.createTask(dto);
    }

//    @GetMapping
//    public List<Tasks> getAllTasks(){
//        return tasksService.getAllTasks();
//    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks(){
        List<Tasks> tasks = tasksService.getAllTasks();
        return tasks.stream().map(tasksService::convertToResponse).toList();
    }

//    @GetMapping("/{id}")
//    public Tasks getTaskById(@PathVariable Long id){
//        return tasksService.getTaskById(id);
//    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id){
        Tasks tasks = tasksService.getTaskById(id);
        return tasksService.convertToResponse(tasks);
    }

    @GetMapping
    public Page<TaskResponseDTO> getTasks(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return tasksService.getTasks(title, page, size, sortBy, direction);
    }


//    @PutMapping("/{id}")
//    public String taskUpdate(@RequestBody Tasks tasks, @PathVariable Long id){
//        tasksService.updateTask(tasks, id);
//        return "Task is updated";
//    }

//    @PutMapping("/{id}")
//    public String taskUpdate(@RequestBody TaskRequestDTO dto, @PathVariable Long id){
//        Tasks updated = tasksService.updateTask(id, dto);
//        tasksService.convertToResponse(updated);
//        return "Task is updated";
//    }

    @PutMapping("/{id}")
    public TaskResponseDTO update(@PathVariable Long id,
                                  @RequestBody TaskRequestDTO dto) {
        return tasksService.updateTask(id, dto);
    }

//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable Long id){
//        tasksService.deleteTask(id);
//    }

    // DELETE Task
    // No DTO needed (unless you want custom response)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        tasksService.deleteTask(id);
        return "Task deleted successfully";
    }

}
