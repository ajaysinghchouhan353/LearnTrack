package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses.stream().filter(Course::isActive).collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));
    }

    public List<Course> getDisabledCourses() {
        return courses.stream().filter(course -> !course.isActive()).collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));
    }

    public Course getCourseById(Long courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public boolean updateCourseStatus(Long courseId, boolean status) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                if(course.isActive() == status) {
                    return false;
                }
                course.setActive(status);
                return true;
            }
        }
        return false;
    }
}
