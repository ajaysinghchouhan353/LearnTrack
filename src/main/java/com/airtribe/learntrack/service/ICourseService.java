package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;

import java.util.List;

public interface ICourseService {
    void addCourse(Course course);
    Course removeCourse(Long courseId);
    Course getCourseById(Long courseId);
    boolean updateCourse(Course course);
    List<Course> getAllCourses();
    boolean setCourseActiveStatus(Long courseId, boolean isActive);
}
