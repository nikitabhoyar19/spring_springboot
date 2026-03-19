package com.guide.studentmanagementsystem.controller;

import com.guide.studentmanagementsystem.dto.StudentDto;
import com.guide.studentmanagementsystem.entity.Student;
import com.guide.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    public StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudent(Model model) {
        List<StudentDto> studentDtoList = studentService.getAllStudents();
        model.addAttribute("students", studentDtoList);
        return "students";
    }

    @PostMapping("/student")
    @ResponseBody//as am using @Controller otherwise with @Restcontroller no need
    public String createStudents(@RequestBody Student student) {
        studentService.createStudent(student);
        return "Student record created";
    }

    @PutMapping("/{id}/student")
    @ResponseBody
    public String updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return "Student record is updated";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student is deleted";
    }
}
