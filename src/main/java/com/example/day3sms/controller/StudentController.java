package com.example.day3sms.controller;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.service.StudentService;
import com.example.day3sms.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {
    private final StudentService service;
    private final JwtUtil jwtUtil;
    public StudentController(StudentService service,  JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    private void checkToken(String authHeader) {
        if (authHeader == null && !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }
    // create function api
    @PostMapping("/add-student")
    public StudentResponseDto addStudent(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody StudentRequestDto student) {
        return service.addStudent(student);
    }

    // Display Students
    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        checkToken(authHeader);
        return service.getAllStudents();
    }

    // Get by id
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@RequestHeader(value = "Authorization",required = false) String authHeader, @PathVariable String id) {
        checkToken(authHeader);
        StudentResponseDto student = service.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@RequestHeader("Authorization") String authHeader, @Valid @PathVariable String id, @RequestBody StudentRequestDto student) {
        checkToken(authHeader);
        StudentResponseDto updated = service.updateStudent(id,student);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@RequestHeader("Authorization") String authHeader, @PathVariable String id) {
        checkToken(authHeader);
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
