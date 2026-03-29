package com.guide.studentmanagementsystem.repository;

import com.guide.studentmanagementsystem.entity.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    Page<Tasks> findByTitleContaining(String title, Pageable pageable);
}
