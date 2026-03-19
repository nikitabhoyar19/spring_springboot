package com.guide.studentmanagementsystem.service;

import com.guide.studentmanagementsystem.dto.StudentDto;
import com.guide.studentmanagementsystem.entity.Student;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    String deleteStudent(long id);
}