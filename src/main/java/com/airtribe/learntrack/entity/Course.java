package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;

public class Course {
    private final Long id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course() {
        this.id = 0L;
        this.courseName = "";
        this.description = "";
        this.durationInWeeks = 0;
        this.active = true;
    }

    public Course(String courseName, String description, int durationInWeeks) {
        this.id = IdGenerator.getNextCourseId();
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void displayCourseInfo() {
        System.out.println("\nCourse ID: " + id);
        System.out.println("Course Name: " + courseName);
        System.out.println("Description: " + description);
        System.out.println("Duration (weeks): " + durationInWeeks);
        System.out.println("Active: " + active);
        System.out.print("----------------------------------");
    }
}
