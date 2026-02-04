package com.airtribe.learntrack.service.Impl;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.IStudentService;

import java.util.List;

public class StudentServiceImpl implements IStudentService {

    private StudentRepository studentRepository;

    StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public Student removeStudent(Long studentId) {
        return null;
    }

    @Override
    public Student getStudentById(Long studentId) {
        return null;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        return List.of();
    }

    @Override
    public boolean setStudentActiveStatus(Long studentId, boolean isActive) {
        return false;
    }
}
