package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.Impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    @Test
    public void addUpdateDeactivateCourse() throws EntityNotFoundException {
        CourseServiceImpl service = new CourseServiceImpl();
        Course c = new Course("C1","Desc",5);
        service.addCourse(c);
        Long id = c.getId();
        assertNotNull(service.getCourseById(id));

        c.setDescription("NewDesc");
        service.updateCourse(c);
        assertEquals("NewDesc", service.getCourseById(id).getDescription());

        service.setCourseActiveStatus(id, false);
        assertFalse(service.getCourseById(id).isActive());
    }
}
