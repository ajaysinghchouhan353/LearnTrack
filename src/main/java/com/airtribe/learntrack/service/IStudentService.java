package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;

import java.util.List;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public interface IStudentService {
    void addStudent(Student student);
    Student getStudentById(Long studentId);
    void updateStudent(Student student) throws EntityNotFoundException;
    List<Student> getAllStudents();
    List<Student> getAllDisabledStudents();
    void setStudentActiveStatus(Long studentId, boolean isActive) throws EntityNotFoundException;
}
