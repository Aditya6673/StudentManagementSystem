package com.example.day3sms.service;

import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    //create
    public StudentModel addStudent(StudentModel student) {
        return repository.save(student);
    }
    //display
    public List<StudentModel> getStudents(){
        return repository.findAll();
    }
    //display by id
    public Optional<StudentModel> getStudentById(String id){
        return repository.findById(id);
    }
    //update
    public StudentModel updateStudent(String id, StudentModel student) {
        return repository.save(student);
    }
    //delete by id
    public void deleteStudentById(String id) {
        repository.deleteById(id);
    }

}
