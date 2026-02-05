package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;

import java.time.Year;

public class Student extends Person{
    private Long StudentID;
    private String courseEnrolled;
    private int batch;
    private boolean active;

    public Student() {
        super();
        this.StudentID = 0L;
        this.courseEnrolled = "";
    }

    public Student(String name, int age) {
        super(name, age);
        this.StudentID = IdGenerator.getNextStudentId();
        this.courseEnrolled = "";
    }

    public Student(String name, int age, String courseEnrolled) {
        super(name, age);
        this.StudentID = IdGenerator.getNextStudentId();
        this.courseEnrolled = courseEnrolled;
    }

    public Student(String name, int age, String email, String courseEnrolled, int batch) {
        this(name, age, courseEnrolled);
        this.setEmail(email);
        this.batch = Year.now().getValue();
        this.active = true;
    }

    public void setName(String name) {
        super.setFirstAndLastName(name);
    }

    public Long getStudentID() {
        return StudentID;
    }

    public String getCourseEnrolled() {
        return courseEnrolled;
    }

    public void setCourseEnrolled(String courseEnrolled) {
        this.courseEnrolled = courseEnrolled;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Course Enrolled: " + courseEnrolled);
        System.out.println("Batch: " + batch);
        System.out.println("Active: " + active);
    }
}
