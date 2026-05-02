package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Year;

public class Student extends Person{
    private static final Logger logger = LoggerFactory.getLogger(Student.class);
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
        logger.info("Student ID: {}", studentId);
        logger.info("Batch: {}", batch);
        logger.info("Active: {}", active);
        logger.info("----------------------------------");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId != null && studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return studentId != null ? studentId.hashCode() : 0;
    }
}
