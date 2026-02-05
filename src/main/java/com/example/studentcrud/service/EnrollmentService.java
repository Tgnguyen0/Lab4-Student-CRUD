package com.example.studentcrud.service;

import com.example.studentcrud.model.Enrollment;
import com.example.studentcrud.model.Student;
import com.example.studentcrud.model.Subject;
import com.example.studentcrud.repository.EnrollmentRepository;
import com.example.studentcrud.repository.StudentRepository;
import com.example.studentcrud.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    public void enrollStudent(Integer studentId, Integer subjectId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        if (student == null || subject == null) return;

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .subject(subject)
                .enrollDate(LocalDate.now())
                .build();

        enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Integer studentId, Integer subjectId) {
        enrollmentRepository.deleteById(new com.example.studentcrud.model.EnrollmentId(studentId, subjectId));
    }
}
