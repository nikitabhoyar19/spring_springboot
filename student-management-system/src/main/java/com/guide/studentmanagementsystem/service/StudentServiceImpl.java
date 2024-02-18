package com.guide.studentmanagementsystem.service;

import com.guide.studentmanagementsystem.dto.StudentDto;
import com.guide.studentmanagementsystem.entity.Student;
import com.guide.studentmanagementsystem.mapper.StudentMapper;
import com.guide.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream().
                map((student)-> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
        return studentDtos;
    }
}
