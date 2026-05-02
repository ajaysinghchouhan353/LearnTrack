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
    public void updateStudent(Student student) throws com.airtribe.learntrack.exception.EntityNotFoundException {
        boolean updated = this.studentRepository.updateStudent(student);
        if (!updated) {
            throw new com.airtribe.learntrack.exception.EntityNotFoundException("Student not found with ID: " + student.getStudentId());
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return this.studentRepository.getStudents();
    }

    @Override
    public List<Student> getAllDisabledStudents() {
        return this.studentRepository.getDisabledStudents();
    }

    @Override
    public void setStudentActiveStatus(Long studentId, boolean isActive) throws com.airtribe.learntrack.exception.EntityNotFoundException {
        boolean updated = this.studentRepository.updateStudentStatus(studentId, isActive);
        if (!updated) {
            throw new com.airtribe.learntrack.exception.EntityNotFoundException("Student not found with ID: " + studentId);
        }
    }
}
