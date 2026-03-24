package com.guide.studentmanagementsystem.service;

import com.guide.studentmanagementsystem.entity.TaskRequestDTO;
import com.guide.studentmanagementsystem.entity.TaskResponseDTO;
import com.guide.studentmanagementsystem.entity.TaskStatus;
import com.guide.studentmanagementsystem.entity.Tasks;
import com.guide.studentmanagementsystem.mapper.TaskMapper;
import com.guide.studentmanagementsystem.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private TaskMapper mapper;

//    public Tasks createTask(Tasks task) {
//        return tasksRepository.save(task);
//    }

    public Tasks convertToEntity(TaskRequestDTO dto) {
        Tasks task = new Tasks();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setTaskStatus(TaskStatus.valueOf(dto.getTaskStatus()));
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        return task;
    }

    // mapstruct mapping
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        Tasks task = mapper.toEntity(dto);
        Tasks saved = tasksRepository.save(task);
        return mapper.toResponse(saved);
    }

    public TaskResponseDTO convertToResponse(Tasks task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setTaskStatus(task.getTaskStatus().name());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Tasks getTaskById(Long id) {
        return tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // mapstruct
    public TaskResponseDTO getTaskMapStructOneById(Long id) {
        Tasks task = tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(task);
    }

    // without mapper
    //    public Tasks updateTask(Tasks tasks, Long id) {
    //        Tasks existingTask = getTaskById(id);
    //        existingTask.setTitle(tasks.getTitle());
    //        existingTask.setDescription(tasks.getDescription());
    //        existingTask.setTaskStatus(tasks.getTaskStatus());
    //        existingTask.setPriority(tasks.getPriority());
    //        existingTask.setDueDate(tasks.getDueDate());
    //        return tasksRepository.save(existingTask);
    //    }

    // manual mapper
    //    public Tasks updateTask(Long id, TaskRequestDTO dto) {
    //        Tasks task = tasksRepository.findById(id)
    //                .orElseThrow(() -> new RuntimeException("Task not found"));
    //
    //        task.setTitle(dto.getTitle());
    //        task.setDescription(dto.getDescription());
    //        task.setTaskStatus(TaskStatus.valueOf(dto.getTaskStatus()));
    //        task.setPriority(dto.getPriority());
    //        task.setDueDate(dto.getDueDate());
    //
    //        return tasksRepository.save(task);
    //    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        Tasks task = tasksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        mapper.updateTaskFromDto(dto, task); // 🔥 auto update

        Tasks updated = tasksRepository.save(task);
        return mapper.toResponse(updated);
    }

    public void deleteTask(Long id){
        tasksRepository.deleteById(id);
        System.out.println("Task {id} deleted");
    }
}
