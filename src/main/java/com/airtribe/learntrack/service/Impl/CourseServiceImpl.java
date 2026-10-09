package com.airtribe.learntrack.service.Impl;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.service.ICourseService;

import java.util.List;

public class CourseServiceImpl implements ICourseService {

    private CourseRepository courseRepository;

    public CourseServiceImpl() {
        this.courseRepository = new CourseRepository();
    }

    @Override
    public void addCourse(Course course) {
        this.courseRepository.addCourse(course);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return this.courseRepository.getCourseById(courseId);
    }

    @Override
    public void updateCourse(Course course) throws com.airtribe.learntrack.exception.EntityNotFoundException {
        Course existingCourse = this.courseRepository.getCourseById(course.getId());
        if (existingCourse != null) {
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setActive(course.isActive());
        } else {
            throw new com.airtribe.learntrack.exception.EntityNotFoundException("Course not found with ID: " + course.getId());
        }
    }

    @Override
    public List<Course> getAllCourses() {
        return this.courseRepository.getCourses();
    }

    @Override
    public List<Course> getAllDisabledCourses() {
        return this.courseRepository.getDisabledCourses();
    }

    @Override
    public void setCourseActiveStatus(Long courseId, boolean isActive) throws com.airtribe.learntrack.exception.EntityNotFoundException {
        boolean updated = this.courseRepository.updateCourseStatus(courseId, isActive);
        if (!updated) {
            throw new com.airtribe.learntrack.exception.EntityNotFoundException("Course not found with ID: " + courseId);
        }
    }
}
