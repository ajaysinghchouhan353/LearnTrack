package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses.stream().filter(Course::isActive).toList();
    }

    public Course getCourseById(Long courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId) && course.isActive()) {
                return course;
            }
        }
        return null;
    }

    public boolean updateCourseStatus(Long courseId, boolean status) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                course.setActive(status);
                return true;
            }
        }
        return false;
    }
}
