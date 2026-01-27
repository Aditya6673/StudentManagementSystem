package com.example.day3sms.controller;

import com.example.day3sms.model.StudentModel;
import com.example.day3sms.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }

    // create function api
    @PostMapping("/add-student")
    public StudentModel addStudent(@RequestBody StudentModel student) {
        return service.addStudent(student);
    }

    // Display Students
    @GetMapping("/students")
    public List<StudentModel> getStudents() {
        return service.getStudents();
    }

    // Get by id
    @GetMapping("/student/{id}")
    public Optional<StudentModel> getStudentById(@PathVariable String id) {
        return service.getStudentById(id);
    }

    // update
    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel student) {
        return service.updateStudent(id,student);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable String id) {
        service.deleteStudentById(id);
    }
}
