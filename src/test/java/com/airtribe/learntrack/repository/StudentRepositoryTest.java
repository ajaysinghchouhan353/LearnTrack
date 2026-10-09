package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryTest {

    @Test
    public void addGetAndDeactivateStudent() {
        StudentRepository repo = new StudentRepository();
        Student s = new Student("Alice","Smith",22);
        repo.addStudent(s);
        Long id = s.getStudentId();
        assertNotNull(repo.getStudentById(id));
        assertTrue(repo.getStudents().stream().anyMatch(st -> st.getStudentId().equals(id)));

        boolean statusUpdated = repo.updateStudentStatus(id, false);
        assertTrue(statusUpdated);
        // now active list should not contain it
        assertFalse(repo.getStudents().stream().anyMatch(st -> st.getStudentId().equals(id)));
        // but getById should still return the entity
        assertNotNull(repo.getStudentById(id));
    }
}
