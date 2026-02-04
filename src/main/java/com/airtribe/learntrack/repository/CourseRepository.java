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
        return courses;
    }

    public Course removeCourse(Long courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                courses.remove(course);
                return course;
            }
        }
        return null;
    }

    public Course getCourseById(Long courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public void updateCourseStatus(Long courseId, boolean status) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                course.setActive(status);
                break;
            }
        }
    }
}
