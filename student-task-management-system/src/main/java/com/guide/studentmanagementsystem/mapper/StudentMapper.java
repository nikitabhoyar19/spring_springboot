package com.guide.studentmanagementsystem.mapper;

import com.guide.studentmanagementsystem.dto.StudentDto;
import com.guide.studentmanagementsystem.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }

    public static Student mapToStudent(StudentDto studentDto) {
        Student student = new Student(
                studentDto.getId(),
                studentDto.getFirstname(),
                studentDto.getLastname(),
                studentDto.getEmail()
        );
        return  student;
    }
}
