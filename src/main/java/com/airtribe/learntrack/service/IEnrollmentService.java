package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.time.LocalDate;
import java.util.List;

public interface IEnrollmentService {
    void enrollStudentInCourse(Student student, Course course, LocalDate enrollmentDate);
    List<Enrollment> viewEnrollmentsByStudent(Student student);
    boolean setEnrollmentStatus(Enrollment enrollment, EnrollmentStatus status);
    List<Enrollment> findEnrollmentByCourse(Long courseId);
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(Long enrollmentId);
}
