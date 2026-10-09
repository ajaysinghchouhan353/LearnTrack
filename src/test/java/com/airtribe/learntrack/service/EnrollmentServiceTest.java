package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.Impl.EnrollmentServiceImpl;
import com.airtribe.learntrack.service.Impl.StudentServiceImpl;
import com.airtribe.learntrack.service.Impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentServiceTest {

    @Test
    public void enrollAndChangeStatus() throws EntityNotFoundException {
        StudentServiceImpl studentService = new StudentServiceImpl();
        CourseServiceImpl courseService = new CourseServiceImpl();
        EnrollmentServiceImpl enrollmentService = new EnrollmentServiceImpl();

        Student s = new Student("Sam","Test",25);
        studentService.addStudent(s);
        Course c = new Course("CS","Intro",3);
        courseService.addCourse(c);

        enrollmentService.enrollStudentInCourse(s, c, LocalDate.now());
        assertEquals(1, enrollmentService.getAllEnrollments().size());

        Enrollment e = enrollmentService.getAllEnrollments().get(0);
        assertEquals(EnrollmentStatus.ACTIVE, e.getStatus());

        enrollmentService.setEnrollmentStatus(e, EnrollmentStatus.COMPLETED);
        assertEquals(EnrollmentStatus.COMPLETED, enrollmentService.getEnrollmentById(e.getId()).getStatus());
    }
}
