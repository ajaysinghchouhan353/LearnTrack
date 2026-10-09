package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;

import java.util.List;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public interface ICourseService {
    void addCourse(Course course);
    Course getCourseById(Long courseId);
    void updateCourse(Course course) throws EntityNotFoundException;
    List<Course> getAllCourses();
    List<Course> getAllDisabledCourses();
    void setCourseActiveStatus(Long courseId, boolean isActive) throws EntityNotFoundException;
}
