package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.time.LocalDate;
import java.util.List;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public interface IEnrollmentService {
    void enrollStudentInCourse(Student student, Course course, LocalDate enrollmentDate);
    List<Enrollment> viewEnrollmentsByStudent(Student student);
    void setEnrollmentStatus(Enrollment enrollment, EnrollmentStatus status) throws EntityNotFoundException;
    List<Enrollment> findEnrollmentByCourse(Long courseId);
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(Long enrollmentId);
}
