package com.airtribe.learntrack.service.Impl;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.IStudentService;

import java.util.List;

public class StudentServiceImpl implements IStudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl() {
        this.studentRepository = new StudentRepository();
    }

    @Override
    public void addStudent(Student student) {
        this.studentRepository.addStudent(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return this.studentRepository.getStudentById(studentId);
    }

    @Override
    public boolean updateStudent(Student student) {
        return this.studentRepository.updateStudent(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return this.studentRepository.getStudents();
    }

    @Override
    public boolean setStudentActiveStatus(Long studentId, boolean isActive) {
        return this.studentRepository.updateStudentStatus(studentId, isActive);
    }
}
