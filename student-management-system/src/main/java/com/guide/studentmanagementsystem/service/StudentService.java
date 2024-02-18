package com.guide.studentmanagementsystem.service;

import com.guide.studentmanagementsystem.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

}