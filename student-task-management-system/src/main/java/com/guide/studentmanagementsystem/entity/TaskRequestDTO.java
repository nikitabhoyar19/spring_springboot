package com.guide.studentmanagementsystem.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    private String title;
    private String description;
    private String taskStatus;
    private Integer priority;
    private LocalDate dueDate;
}
