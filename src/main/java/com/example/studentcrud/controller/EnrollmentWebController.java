package com.example.studentcrud.controller;

import com.example.studentcrud.repository.StudentRepository;
import com.example.studentcrud.repository.SubjectRepository;
import com.example.studentcrud.service.EnrollmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/enrollments")
public class EnrollmentWebController {

    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public EnrollmentWebController(EnrollmentService enrollmentService,
                                   StudentRepository studentRepository,
                                   SubjectRepository subjectRepository) {
        this.enrollmentService = enrollmentService;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public String listEnrollments(Model model) {
        model.addAttribute("enrollments", enrollmentService.getAll());
        return "enrollments";
    }

    @GetMapping("/add")
    public String showEnrollForm(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "add-enrollment";
    }

    @PostMapping("/save")
    public String saveEnrollment(@RequestParam Integer studentId,
                                 @RequestParam Integer subjectId) {
        enrollmentService.enrollStudent(studentId, subjectId);
        return "redirect:/web/enrollments";
    }

    @GetMapping("/delete")
    public String deleteEnrollment(@RequestParam Integer studentId,
                                   @RequestParam Integer subjectId) {
        enrollmentService.deleteEnrollment(studentId, subjectId);
        return "redirect:/web/enrollments";
    }
}
