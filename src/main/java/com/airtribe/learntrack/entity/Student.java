package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;

import java.time.Year;

public class Student extends Person{
    private final Long StudentID;
    private int batch;
    private boolean active=true;

    public Student() {
        super();
        this.StudentID = 0L;
    }

    public Student(String name, int age) {
        super(name, age);
        this.StudentID = IdGenerator.getNextStudentId();
    }

    public Student(String name, int age, String email) {
        this(name, age);
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
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Student ID: " + StudentID);
        System.out.println("Batch: " + batch);
        System.out.println("Active: " + active);
    }
}
