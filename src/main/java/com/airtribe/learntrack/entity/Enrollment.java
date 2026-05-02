package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.utils.IdGenerator;

import java.time.LocalDate;

public class Enrollment {
    private final Long id;
    private final Student student;
    private final Course course;
    private final LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment(Student student, Course course, LocalDate enrollmentDate) {
        this.id = IdGenerator.getNextEnrollmentId();
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = EnrollmentStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public void displayEnrollmentDetails() {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Enrollment.class);
        logger.info("Enrollment ID: {}", id);
        logger.info("Student: {}", (student != null ? student.getName() : "N/A"));
        logger.info("Course: {}", (course != null ? course.getCourseName() : "N/A"));
        logger.info("Enrollment Date: {}", enrollmentDate);
        logger.info("Status: {}", status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
