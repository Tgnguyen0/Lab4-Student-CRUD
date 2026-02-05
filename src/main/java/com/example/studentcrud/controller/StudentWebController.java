package com.example.studentcrud.controller;

import com.example.studentcrud.model.Student;
import com.example.studentcrud.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/students")
public class StudentWebController {

    private final StudentService service;

    public StudentWebController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", service.getAll());
        return "students";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/web/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Integer id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "edit-student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/web/students";
    }

    @GetMapping("/search")
    public String searchStudentById(@RequestParam("id") Integer id, Model model) {

        Student student = service.getById(id);

        if (student != null) {
            model.addAttribute("students", java.util.List.of(student));
        } else {
            model.addAttribute("students", java.util.List.of());
            model.addAttribute("error", "Không tìm thấy sinh viên với ID = " + id);
        }

        return "students";
    }
}
