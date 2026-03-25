package com.guide.studentmanagementsystem.entity;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "PENDING|COMPLETED|IN_PROGRESS", message = "Invalid status")
    private String taskStatus;
    private Integer priority;

    @Future(message = "Due date must be in future")
    private LocalDate dueDate;
}
