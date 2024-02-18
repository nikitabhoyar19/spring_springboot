package com.guide.studentmanagementsystem.controller;

import com.guide.studentmanagementsystem.dto.StudentDto;
import com.guide.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
