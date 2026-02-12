package com.airtribe.learntrack.service.Impl;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.service.IEnrollmentService;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentServiceImpl implements IEnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl() {
        this.enrollmentRepository = new EnrollmentRepository();
    }

    @Override
    public void enrollStudentInCourse(Student student, Course course, LocalDate enrollmentDate) {
        Enrollment enrollment = new Enrollment(student, course, enrollmentDate);
        this.enrollmentRepository.addEnrollment(enrollment);
    }

    @Override
    public List<Enrollment> viewEnrollmentsByStudent(Student student) {
        return this.enrollmentRepository.findEnrollmentByStudent(student);
    }

    @Override
    public boolean setEnrollmentStatus(Enrollment enrollment, EnrollmentStatus status) {
        return this.enrollmentRepository.updateEnrollmentStatus(enrollment, status);
    }

    @Override
    public List<Enrollment> findEnrollmentByCourse(Long courseId) {
        return this.enrollmentRepository.findEnrollmentByCourseId(courseId);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return this.enrollmentRepository.getAllEnrollments();
    }

    @Override
    public Enrollment getEnrollmentById(Long enrollmentId) {
        return this.enrollmentRepository.getEnrollmentById(enrollmentId);
    }
}
