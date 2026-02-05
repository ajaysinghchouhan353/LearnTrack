package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    List<Enrollment> enrollments = new ArrayList<>();

    public List<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void updateEnrollmentStatus(Enrollment enrollment, EnrollmentStatus newStatus) {
        enrollment.setStatus(newStatus);
    }

    public Enrollment findEnrollmentByStudentId(Student student) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().equals(student)) {
                return enrollment;
            }
        }
        return null;
    }

    public Enrollment findEnrollmentByCourseId(Long courseID) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseID().equals(courseID)) {
                return enrollment;
            }
        }
        return null;
    }
}
