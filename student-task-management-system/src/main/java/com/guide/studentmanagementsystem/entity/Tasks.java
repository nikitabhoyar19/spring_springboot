package com.guide.studentmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;        // Task name
    private String description;  // Details about task

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;       // PENDING, IN_PROGRESS, DONE

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;   // Deadline

    private Integer priority;    // 1 = High, 2 = Medium, 3 = Low

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
