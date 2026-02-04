package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;

public class Course {
    private Long id;
    private String courseName;
    private String description;
    private String durationInWeeks;
    private boolean active;

    public Course() {
        this.id = 0L;
        this.courseName = "";
        this.description = "";
        this.durationInWeeks = "";
        this.active = true;
    }

    public Course(String courseName, String description, String durationInWeeks) {
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

    public String getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(String durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
