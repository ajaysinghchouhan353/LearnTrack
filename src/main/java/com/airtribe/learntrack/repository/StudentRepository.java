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
        return students;
    }

    public Student removeStudent(Long studentId) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                students.remove(student);
                return student;
            }
        }
        return null;
    }

    public Student getStudentById(Long studentId) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudentStatus(Long studentId, boolean status) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                student.setActive(status);
                break;
            }
        }
    }
}
