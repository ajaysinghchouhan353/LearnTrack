package com.airtribe.learntrack.service.Impl;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.service.ICourseService;

import java.util.List;

public class CourseServiceImpl implements ICourseService {

    private CourseRepository courseRepository;

    CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void addCourse(Course course) {

    }

    @Override
    public Course removeCourse(Long courseId) {
        return null;
    }

    @Override
    public Course getCourseById(Long courseId) {
        return null;
    }

    @Override
    public boolean updateCourse(Course course) {
        return false;
    }

    @Override
    public List<Course> getAllCourses() {
        return List.of();
    }

    @Override
    public boolean setCourseActiveStatus(Long courseId, boolean isActive) {
        return false;
    }
}
