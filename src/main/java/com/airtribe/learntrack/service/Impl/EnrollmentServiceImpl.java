package com.airtribe.learntrack.service.Impl;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.service.IEnrollmentService;

import java.util.List;

public class EnrollmentServiceImpl implements IEnrollmentService {

    private EnrollmentRepository enrollmentRepository;

    EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void enrollStudentInCourse(Enrollment enrollment) {

    }

    @Override
    public List<Enrollment> viewEnrollmentsByStudent(Long studentId) {
        return List.of();
    }

    @Override
    public boolean setEnrollmentStatus(Long enrollmentId, EnrollmentStatus status) {
        return false;
    }
}
