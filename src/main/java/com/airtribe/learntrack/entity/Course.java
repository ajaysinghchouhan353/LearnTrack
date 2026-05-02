package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Course {
    private static final Logger logger = LoggerFactory.getLogger(Course.class);
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

    public int getDurationInWeeks() {
        return durationInWeeks;
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
        logger.info("\nCourse ID: {}", id);
        logger.info("Course Name: {}", courseName);
        logger.info("Description: {}", description);
        logger.info("Duration (weeks): {}", durationInWeeks);
        logger.info("Active: {}", active);
        logger.info("----------------------------------");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id != null && id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
