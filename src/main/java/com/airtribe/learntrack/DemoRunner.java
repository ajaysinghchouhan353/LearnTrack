package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.ICourseService;
import com.airtribe.learntrack.service.IEnrollmentService;
import com.airtribe.learntrack.service.IStudentService;
import com.airtribe.learntrack.utils.FactoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class DemoRunner {
    private static final Logger logger = LoggerFactory.getLogger(DemoRunner.class);

    public static void main(String[] args) {
        logger.info("Running non-interactive LearnTrack demo...");
        FactoryService factory = new FactoryService();
        ICourseService courseService = factory.getCourseService();
        IStudentService studentService = factory.getStudentService();
        IEnrollmentService enrollmentService = factory.getEnrollmentService();

        try {
            logger.info("--- Course Setup ---");
            Course javaCourse = new Course("Java 101", "Core Java fundamentals", 6);
            Course springCourse = new Course("Spring Boot", "Building production-ready APIs", 8);
            courseService.addCourse(javaCourse);
            courseService.addCourse(springCourse);
            logger.info("Added courses with ids {} and {}", javaCourse.getId(), springCourse.getId());

            springCourse.setDescription("Spring Boot for REST services");
            springCourse.setDurationInWeeks(10);
            courseService.updateCourse(springCourse);
            logger.info("Updated course {} to '{}' ({} weeks)", springCourse.getId(), springCourse.getCourseName(), springCourse.getDurationInWeeks());

            logger.info("Active courses after setup: {}", courseService.getAllCourses().size());
            printCourses("Active courses", courseService.getAllCourses());

            logger.info("--- Student Setup ---");
            Student alice = new Student("Alice", "Johnson", 20);
            Student bob = new Student("Bob", "Williams", 22);
            studentService.addStudent(alice);
            studentService.addStudent(bob);
            logger.info("Added students with ids {} and {}", alice.getStudentId(), bob.getStudentId());

            alice.setAge(21);
            alice.setEmail("alice@example.com");
            studentService.updateStudent(alice);
            logger.info("Updated student {} with new age and email", alice.getStudentId());

            studentService.setStudentActiveStatus(bob.getStudentId(), false);
            logger.info("Deactivated student {}", bob.getStudentId());
            studentService.setStudentActiveStatus(bob.getStudentId(), true);
            logger.info("Reactivated student {}", bob.getStudentId());

            logger.info("Active students after setup: {}", studentService.getAllStudents().size());
            printStudents("Active students", studentService.getAllStudents());

            logger.info("--- Enrollment Lifecycle ---");
            enrollmentService.enrollStudentInCourse(alice, javaCourse, LocalDate.now().minusDays(2));
            enrollmentService.enrollStudentInCourse(alice, springCourse, LocalDate.now().minusDays(1));
            enrollmentService.enrollStudentInCourse(bob, javaCourse, LocalDate.now());
            logger.info("Total enrollments after creation: {}", enrollmentService.getAllEnrollments().size());

            List<Enrollment> aliceEnrollments = enrollmentService.viewEnrollmentsByStudent(alice);
            printEnrollments("Alice enrollments", aliceEnrollments);

            if (!aliceEnrollments.isEmpty()) {
                Enrollment firstEnrollment = aliceEnrollments.get(0);
                enrollmentService.setEnrollmentStatus(firstEnrollment, EnrollmentStatus.COMPLETED);
                logger.info("Marked enrollment {} as COMPLETED", firstEnrollment.getId());
            }

            List<Enrollment> javaEnrollments = enrollmentService.findEnrollmentByCourse(javaCourse.getId());
            logger.info("Enrollments for Java course before deactivation: {}", javaEnrollments.size());
            printEnrollments("Java course enrollments", javaEnrollments);

            deactivateCourseWithCascade(courseService, enrollmentService, javaCourse.getId());
            printEnrollments("Java course enrollments after deactivation", enrollmentService.findEnrollmentByCourse(javaCourse.getId()));

            courseService.setCourseActiveStatus(javaCourse.getId(), true);
            logger.info("Reactivated course {}", javaCourse.getId());

            logger.info("--- Student Deactivation Cascade ---");
            List<Enrollment> bobEnrollmentsBefore = enrollmentService.viewEnrollmentsByStudent(bob);
            printEnrollments("Bob enrollments before deactivation", bobEnrollmentsBefore);
            deactivateStudentWithCascade(studentService, enrollmentService, bob);
            printEnrollments("Bob enrollments after deactivation", enrollmentService.viewEnrollmentsByStudent(bob));
            studentService.setStudentActiveStatus(bob.getStudentId(), true);
            logger.info("Reactivated student {}", bob.getStudentId());

            logger.info("--- Final Snapshot ---");
            printCourses("All active courses", courseService.getAllCourses());
            printCourses("Disabled courses", courseService.getAllDisabledCourses());
            printStudents("All active students", studentService.getAllStudents());
            printStudents("Disabled students", studentService.getAllDisabledStudents());
            printEnrollments("All enrollments", enrollmentService.getAllEnrollments());

            logger.info("Demo finished successfully.");
        } catch (EntityNotFoundException ex) {
            logger.error("Demo error: {}", ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error("Unexpected demo failure: {}", ex.getMessage(), ex);
        }
    }

    private static void printCourses(String label, List<Course> courses) {
        logger.info("{}: {}", label, courses.size());
        courses.forEach(Course::displayCourseInfo);
    }

    private static void printStudents(String label, List<Student> students) {
        logger.info("{}: {}", label, students.size());
        students.forEach(Student::displayInfo);
    }

    private static void printEnrollments(String label, List<Enrollment> enrollments) {
        logger.info("{}: {}", label, enrollments.size());
        enrollments.forEach(Enrollment::displayEnrollmentDetails);
    }

    private static void deactivateCourseWithCascade(ICourseService courseService, IEnrollmentService enrollmentService, Long courseId)
            throws EntityNotFoundException {
        List<Enrollment> enrollments = enrollmentService.findEnrollmentByCourse(courseId);
        for (Enrollment enrollment : enrollments) {
            enrollmentService.setEnrollmentStatus(enrollment, EnrollmentStatus.CANCELLED);
        }
        courseService.setCourseActiveStatus(courseId, false);
        logger.info("Deactivated course {} and cancelled {} linked enrollments", courseId, enrollments.size());
    }

    private static void deactivateStudentWithCascade(IStudentService studentService, IEnrollmentService enrollmentService, Student student)
            throws EntityNotFoundException {
        List<Enrollment> enrollments = enrollmentService.viewEnrollmentsByStudent(student);
        for (Enrollment enrollment : enrollments) {
            enrollmentService.setEnrollmentStatus(enrollment, EnrollmentStatus.CANCELLED);
        }
        studentService.setStudentActiveStatus(student.getStudentId(), false);
        logger.info("Deactivated student {} and cancelled {} linked enrollments", student.getStudentId(), enrollments.size());
    }
}
