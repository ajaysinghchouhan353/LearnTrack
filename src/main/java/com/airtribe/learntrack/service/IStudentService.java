package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;

import java.util.List;

public interface IStudentService {
    void addStudent(Student student);
    Student getStudentById(Long studentId);
    boolean updateStudent(Student student);
    List<Student> getAllStudents();
    boolean setStudentActiveStatus(Long studentId, boolean isActive);
}
