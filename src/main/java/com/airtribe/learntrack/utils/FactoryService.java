package com.airtribe.learntrack.utils;

import com.airtribe.learntrack.service.ICourseService;
import com.airtribe.learntrack.service.IEnrollmentService;
import com.airtribe.learntrack.service.IStudentService;
import com.airtribe.learntrack.service.Impl.CourseServiceImpl;
import com.airtribe.learntrack.service.Impl.EnrollmentServiceImpl;
import com.airtribe.learntrack.service.Impl.StudentServiceImpl;

public class FactoryService {
    ICourseService courseService;
    IStudentService studentService;
    IEnrollmentService enrollmentService;

    public FactoryService() {
        this.courseService = new CourseServiceImpl();
        this.studentService = new StudentServiceImpl();
        this.enrollmentService = new EnrollmentServiceImpl();
    }

    public ICourseService getCourseService() {
        return courseService;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public IEnrollmentService getEnrollmentService() {
        return enrollmentService;
    }
}
