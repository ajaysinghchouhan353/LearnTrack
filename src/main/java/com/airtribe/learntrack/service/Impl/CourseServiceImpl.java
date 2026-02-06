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
    public boolean updateCourse(Course course) {
        Course existingCourse = this.courseRepository.getCourseById(course.getId());
        if (existingCourse != null) {
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setActive(course.isActive());
            return true;
        }
        return false;
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
    public boolean setCourseActiveStatus(Long courseId, boolean isActive) {
        return this.courseRepository.updateCourseStatus(courseId, isActive);
    }
}
