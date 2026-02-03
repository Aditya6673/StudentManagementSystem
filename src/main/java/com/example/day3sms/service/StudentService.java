package com.example.day3sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.exception.StudentNotFoundException;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Conversion methods
    private StudentModel toEntity(StudentRequestDto dto) {
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        return student;
    }

    private StudentResponseDto toResponseDto(StudentModel student) {
        return new StudentResponseDto(student.getId(), student.getName(), student.getAge(), student.getEmail());
    }

    // Create
    public StudentResponseDto addStudent(StudentRequestDto studentDto) {
        StudentModel student = toEntity(studentDto);
        StudentModel savedStudent = repository.save(student);
        return toResponseDto(savedStudent);
    }

    // Read All
    public List<StudentResponseDto> getAllStudents() {
        return repository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // Read by ID
    public StudentResponseDto getStudentById(String id) {
        return repository.findById(id)
                .map(this::toResponseDto)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    // Update
    public StudentResponseDto updateStudent(String id, StudentRequestDto studentDto) {
        // Check if student exists
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        StudentModel student = toEntity(studentDto);
        student.setId(id);
        StudentModel updatedStudent = repository.save(student);
        return toResponseDto(updatedStudent);
    }

    // Delete
    public void deleteStudent(String id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        repository.deleteById(id);
    }
}