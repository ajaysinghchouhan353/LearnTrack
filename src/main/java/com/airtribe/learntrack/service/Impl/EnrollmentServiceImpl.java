package com.airtribe.learntrack.service.Impl;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.service.IEnrollmentService;

import java.util.List;

public class EnrollmentServiceImpl implements IEnrollmentService {

    private EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl() {
        this.enrollmentRepository = new EnrollmentRepository();
    }

    @Override
    public void enrollStudentInCourse(Student student, Course course, String enrollmentDate) {
        Enrollment enrollment = new Enrollment(student, course, enrollmentDate);
        this.enrollmentRepository.addEnrollment(enrollment);
    }

    @Override
    public Enrollment viewEnrollmentsByStudent(Student student) {
        return this.enrollmentRepository.findEnrollmentByStudentId(student);
    }

    @Override
    public void setEnrollmentStatus(Enrollment enrollment, EnrollmentStatus status) {
        this.enrollmentRepository.updateEnrollmentStatus(enrollment, status);
    }

    @Override
    public Enrollment findEnrollmentByCourse(Course course) {
        return this.enrollmentRepository.findEnrollmentByCourseId(course.getId());
    }
}
