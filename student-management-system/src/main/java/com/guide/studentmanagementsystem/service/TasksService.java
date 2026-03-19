package com.guide.studentmanagementsystem.service;

import com.guide.studentmanagementsystem.entity.Tasks;
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

    public Tasks createTask(Tasks task) {
        return tasksRepository.save(task);
    }

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Tasks getTaskById(Long id) {
        return tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Tasks updateTask(Tasks tasks, Long id) {
        Tasks existingTask = getTaskById(id);
        existingTask.setTitle(tasks.getTitle());
        existingTask.setDescription(tasks.getDescription());
        existingTask.setTaskStatus(tasks.getTaskStatus());
        existingTask.setPriority(tasks.getPriority());
        existingTask.setDueDate(tasks.getDueDate());
        return tasksRepository.save(existingTask);
    }

    public void deleteTask(Long id){
        tasksRepository.deleteById(id);
    }
}
