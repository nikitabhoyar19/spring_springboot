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

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student existentStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found" + id));

        existentStudent.setFirstname(student.getFirstname());
        existentStudent.setLastname(student.getLastname());
        existentStudent.setEmail(student.getEmail());
        return studentRepository.save(existentStudent);
    }

    @Override
    public String deleteStudent(long id) {
        Student existStudentWithId = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found here with " + id));

        studentRepository.delete(existStudentWithId);
        return "student is deleted";
    }
}
