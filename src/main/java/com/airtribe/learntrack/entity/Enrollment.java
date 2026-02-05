package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.utils.IdGenerator;

public class Enrollment {
    private Long id;
    private Student student;
    private Course course;
    private String enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment() {
        this.id = 0L;
        this.student = null;
        this.course = null;
        this.enrollmentDate = "";
        this.status = EnrollmentStatus.ACTIVE;
    }

    public Enrollment(Student student, Course course, String enrollmentDate) {
        this.id = IdGenerator.getNextEnrollmentId();
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourseID() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}
