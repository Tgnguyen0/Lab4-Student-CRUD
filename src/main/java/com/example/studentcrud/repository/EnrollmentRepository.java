package com.example.studentcrud.repository;

import com.example.studentcrud.model.Enrollment;
import com.example.studentcrud.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
}
