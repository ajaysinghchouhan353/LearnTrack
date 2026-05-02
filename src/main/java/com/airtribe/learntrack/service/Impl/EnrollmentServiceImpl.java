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
    public void setEnrollmentStatus(Enrollment enrollment, EnrollmentStatus status) throws com.airtribe.learntrack.exception.EntityNotFoundException {
        boolean updated = this.enrollmentRepository.updateEnrollmentStatus(enrollment, status);
        if (!updated) {
            throw new com.airtribe.learntrack.exception.EntityNotFoundException("Enrollment not found with ID: " + (enrollment != null ? enrollment.getId() : "null"));
        }
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
