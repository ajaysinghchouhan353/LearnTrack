package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;

import java.time.Year;

public class Student extends Person{
    private final Long studentId = IdGenerator.getNextStudentId();
    private int batch;
    private boolean active=true;

    public Student() {
        super();
    }

    public Student(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    public Student(String firstName, String lastName, int age, String email) {
        this(firstName, lastName, age);
        this.setEmail(email);
        this.batch = Year.now().getValue();
        this.active = true;
    }

    public void setName(String name) {
        super.setFirstName(name);
    }

    public Long getStudentId() {
        return studentId;
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
        System.out.println("Student ID: " + studentId);
        System.out.println("Batch: " + batch);
        System.out.println("Active: " + active);
        System.out.println("----------------------------------");
    }
}
