package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.utils.IdGenerator;

public class Enrollment {
    private final Long id;
    private final Student student;
    private final Course course;
    private final String enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment(Student student, Course course, String enrollmentDate) {
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
        System.out.println("---------------------------");
        System.out.println("Enrollment ID: " + id);
        System.out.println("Student: " + (student != null ? student.getName() : "N/A"));
        System.out.println("Course: " + (course != null ? course.getCourseName() : "N/A"));
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Status: " + status);
    }
}
