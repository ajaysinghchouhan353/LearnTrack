package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.util.List;

public interface IEnrollmentService {
    void enrollStudentInCourse(Student student, Course course, String enrollmentDate);
    List<Enrollment> viewEnrollmentsByStudent(Student student);
    void setEnrollmentStatus(Enrollment enrollmentId, EnrollmentStatus status);
    List<Enrollment> findEnrollmentByCourse(Long courseId);
    List<Enrollment> getAllEnrollments();
}
