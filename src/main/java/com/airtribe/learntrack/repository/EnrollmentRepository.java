package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnrollmentRepository {
    List<Enrollment> enrollments = new ArrayList<>();

    public List<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void updateEnrollmentStatus(Enrollment enrollment, EnrollmentStatus newStatus) {
        for(Enrollment e: enrollments) {
            if(e.getId().equals(enrollment.getId())) {
                e.setStatus(newStatus);
                break;
            }
        }
    }

    public List<Enrollment> findEnrollmentByStudent(Student student) {
        List<Enrollment> enrollmentList = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().equals(student)) {
                enrollmentList.add(enrollment);
            }
        }
        return enrollmentList;
    }

    public List<Enrollment> findEnrollmentByCourseId(Long courseId) {
        List<Enrollment> enrollmentList = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (Objects.equals(enrollment.getCourse().getId(), courseId)) {
                enrollmentList.add(enrollment);
            }
        }
        return enrollmentList;
    }
}
