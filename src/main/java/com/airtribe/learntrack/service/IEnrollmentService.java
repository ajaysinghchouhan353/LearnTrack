package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.util.List;

public interface IEnrollmentService {
    void enrollStudentInCourse(Enrollment enrollment);
    List<Enrollment> viewEnrollmentsByStudent(Long studentId);
    boolean setEnrollmentStatus(Long enrollmentId, EnrollmentStatus status);
}
