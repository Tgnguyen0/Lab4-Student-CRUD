package com.example.studentcrud.controller;

import com.example.studentcrud.model.Student;
import com.example.studentcrud.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.save(student);
    }
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        return service.getById(id);
    }
    @DeleteMapping("/{id}")
    public Student deleteById(@PathVariable Integer id) {
        return service.getById(id);
    }
}
