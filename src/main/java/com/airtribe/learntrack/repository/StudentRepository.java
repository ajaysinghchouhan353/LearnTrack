package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students.stream().filter(Student::isActive).toList();
    }

    public Student getStudentById(Long studentId) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentId) && student.isActive()) {
                return student;
            }
        }
        return null;
    }

    public boolean updateStudentStatus(Long studentId, boolean status) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                student.setActive(status);
                return true;
            }
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(student.getStudentID()) && students.get(i).isActive()) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }
}
