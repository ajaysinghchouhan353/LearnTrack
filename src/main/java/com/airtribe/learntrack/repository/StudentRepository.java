package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students.stream().filter(Student::isActive).collect(Collectors.toList());
    }

    public List<Student> getDisabledStudents() {
        return students.stream().filter(student -> !student.isActive()).collect(Collectors.toList());
    }

    public Student getStudentById(Long studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId) && student.isActive()) {
                return student;
            }
        }
        return null;
    }

    public boolean updateStudentStatus(Long studentId, boolean status) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.setActive(status);
                return true;
            }
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(student.getStudentId()) && students.get(i).isActive()) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }
}
