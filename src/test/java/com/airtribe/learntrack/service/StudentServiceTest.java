package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.Impl.StudentServiceImpl;
import com.airtribe.learntrack.repository.StudentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    public void addUpdateAndDeactivateStudent() throws EntityNotFoundException {
        StudentServiceImpl service = new StudentServiceImpl();
        Student s = new Student("Bob","Builder",30);
        service.addStudent(s);
        Long id = s.getStudentId();
        Student fetched = service.getStudentById(id);
        assertNotNull(fetched);

        // update
        s.setAge(31);
        service.updateStudent(s);
        Student updated = service.getStudentById(id);
        assertEquals(31, updated.getAge());

        // deactivate
        service.setStudentActiveStatus(id, false);
        assertFalse(service.getStudentById(id).isActive());

        // try update non-existing
        Student fake = new Student("X","Y",20);
        try {
            service.updateStudent(fake);
            fail("Expected EntityNotFoundException for non-existing student");
        } catch (EntityNotFoundException ex) {
            // expected
        }
    }
}
