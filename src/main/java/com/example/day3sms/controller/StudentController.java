package com.example.day3sms.controller;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public StudentResponseDto addStudent(@Valid @RequestBody StudentRequestDto student) {
        return service.addStudent(student);
    }

    // Display Students
    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents() {
        return service.getAllStudents();
    }

    // Get by id
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable String id) {
        StudentResponseDto student = service.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@Valid @PathVariable String id, @RequestBody StudentRequestDto student) {
        StudentResponseDto updated = service.updateStudent(id,student);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
