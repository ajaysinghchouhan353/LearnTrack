package com.airtribe.learntrack.integration;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.ICourseService;
import com.airtribe.learntrack.service.IEnrollmentService;
import com.airtribe.learntrack.service.IStudentService;
import com.airtribe.learntrack.utils.FactoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LearnTrack Integration Tests")
public class LearnTrackIntegrationTest {

    private IStudentService studentService;
    private ICourseService courseService;
    private IEnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        FactoryService factory = new FactoryService();
        studentService = factory.getStudentService();
        courseService = factory.getCourseService();
        enrollmentService = factory.getEnrollmentService();
    }

    @Test
    @DisplayName("Integration: Add Student and Course successfully")
    void testAddStudentAndCourse() {
        Student student = new Student("Alice", "Smith", 20);
        studentService.addStudent(student);

        Course course = new Course("Java Basics", "Learn Java fundamentals", 4);
        courseService.addCourse(course);

        assertNotNull(student.getStudentId());
        assertNotNull(course.getId());
        assertTrue(student.isActive());
        assertTrue(course.isActive());
        assertTrue(studentService.getAllStudents().size() > 0);
        assertTrue(courseService.getAllCourses().size() > 0);
    }

    @Test
    @DisplayName("Integration: Complete enrollment workflow")
    void testCompleteEnrollmentWorkflow() throws EntityNotFoundException, InvalidInputException {
        Student student = new Student("Bob", "Johnson", 21);
        studentService.addStudent(student);

        Course course = new Course("SpringBoot", "Spring Boot mastery", 6);
        courseService.addCourse(course);

        enrollmentService.enrollStudentInCourse(student, course, LocalDate.now());
        List<Enrollment> enrollments = enrollmentService.viewEnrollmentsByStudent(student);

        assertTrue(enrollments.size() > 0);
        Enrollment enrollment = enrollments.get(0);
        assertEquals(EnrollmentStatus.ACTIVE, enrollment.getStatus());

        enrollmentService.setEnrollmentStatus(enrollment, EnrollmentStatus.COMPLETED);
        Enrollment updated = enrollmentService.getEnrollmentById(enrollment.getId());
        assertEquals(EnrollmentStatus.COMPLETED, updated.getStatus());
    }

    @Test
    @DisplayName("Integration: Multiple students, courses, and enrollments")
    void testMultipleEntitiesWorkflow() throws EntityNotFoundException, InvalidInputException {
        Student alice = new Student("Alice", "A", 20);
        Student bob = new Student("Bob", "B", 21);
        Student charlie = new Student("Charlie", "C", 22);
        studentService.addStudent(alice);
        studentService.addStudent(bob);
        studentService.addStudent(charlie);

        Course course1 = new Course("Java", "Java programming", 4);
        Course course2 = new Course("Python", "Python programming", 5);
        courseService.addCourse(course1);
        courseService.addCourse(course2);

        enrollmentService.enrollStudentInCourse(alice, course1, LocalDate.now());
        enrollmentService.enrollStudentInCourse(alice, course2, LocalDate.now());
        enrollmentService.enrollStudentInCourse(bob, course1, LocalDate.now());

        assertTrue(enrollmentService.getAllEnrollments().size() >= 3);
        assertTrue(enrollmentService.viewEnrollmentsByStudent(alice).size() >= 2);
        assertTrue(enrollmentService.viewEnrollmentsByStudent(bob).size() >= 1);
        assertEquals(0, enrollmentService.viewEnrollmentsByStudent(charlie).size());
    }

    @Test
    @DisplayName("Integration: Update course details and verify")
    void testUpdateCourseWorkflow() throws EntityNotFoundException, InvalidInputException {
        Course course = new Course("Ruby", "Ruby on Rails", 3);
        courseService.addCourse(course);
        Long courseId = course.getId();

        Course retrieved = courseService.getCourseById(courseId);
        retrieved.setCourseName("Ruby on Rails Advanced");
        retrieved.setDurationInWeeks(5);
        courseService.updateCourse(retrieved);

        Course updated = courseService.getCourseById(courseId);
        assertEquals("Ruby on Rails Advanced", updated.getCourseName());
        assertEquals(5, updated.getDurationInWeeks());
    }

    @Test
    @DisplayName("Integration: Reactivate deactivated student")
    void testReactivateStudent() throws EntityNotFoundException {
        Student student = new Student("Frank", "Gray", 30);
        studentService.addStudent(student);

        studentService.setStudentActiveStatus(student.getStudentId(), false);
        assertFalse(studentService.getStudentById(student.getStudentId()).isActive());

        studentService.setStudentActiveStatus(student.getStudentId(), true);
        assertTrue(studentService.getStudentById(student.getStudentId()).isActive());
    }

    @Test
    @DisplayName("Integration: Reactivate deactivated course")
    void testReactivateCourse() throws EntityNotFoundException {
        Course course = new Course("Go", "Go programming", 4);
        courseService.addCourse(course);

        courseService.setCourseActiveStatus(course.getId(), false);
        assertFalse(courseService.getCourseById(course.getId()).isActive());

        courseService.setCourseActiveStatus(course.getId(), true);
        assertTrue(courseService.getCourseById(course.getId()).isActive());
    }

    @Test
    @DisplayName("Integration: Enrollment status transitions")
    void testEnrollmentStatusTransitions() throws EntityNotFoundException, InvalidInputException {
        Student student = new Student("Jack", "Adams", 31);
        studentService.addStudent(student);

        Course course = new Course("Kotlin", "Kotlin development", 3);
        courseService.addCourse(course);

        enrollmentService.enrollStudentInCourse(student, course, LocalDate.now());
        Enrollment enrollment = enrollmentService.viewEnrollmentsByStudent(student).get(0);
        assertEquals(EnrollmentStatus.ACTIVE, enrollment.getStatus());

        enrollmentService.setEnrollmentStatus(enrollment, EnrollmentStatus.COMPLETED);
        assertEquals(EnrollmentStatus.COMPLETED, enrollmentService.getEnrollmentById(enrollment.getId()).getStatus());

        enrollmentService.setEnrollmentStatus(enrollment, EnrollmentStatus.ACTIVE);
        assertEquals(EnrollmentStatus.ACTIVE, enrollmentService.getEnrollmentById(enrollment.getId()).getStatus());

        enrollmentService.setEnrollmentStatus(enrollment, EnrollmentStatus.CANCELLED);
        assertEquals(EnrollmentStatus.CANCELLED, enrollmentService.getEnrollmentById(enrollment.getId()).getStatus());
    }
}
