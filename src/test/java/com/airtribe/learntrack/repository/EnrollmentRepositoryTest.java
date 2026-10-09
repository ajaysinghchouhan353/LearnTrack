package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentRepositoryTest {

    @Test
    public void addAndFindEnrollmentByStudentAndId() {
        EnrollmentRepository repo = new EnrollmentRepository();
        Student s = new Student("Test","Student",20);
        Course c = new Course("Course","Desc",4);
        Enrollment e = new Enrollment(s, c, LocalDate.now());
        repo.addEnrollment(e);

        assertEquals(1, repo.getAllEnrollments().size());
        assertNotNull(repo.getEnrollmentById(e.getId()));
        assertEquals(e.getId(), repo.getEnrollmentById(e.getId()).getId());

        assertEquals(1, repo.findEnrollmentByStudent(s).size());

        boolean updated = repo.updateEnrollmentStatus(e, EnrollmentStatus.COMPLETED);
        assertTrue(updated);
        assertEquals(EnrollmentStatus.COMPLETED, repo.getEnrollmentById(e.getId()).getStatus());
    }
}
