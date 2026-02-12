package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnrollmentRepository {
    private List<Enrollment> enrollments = new ArrayList<>();

    public List<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public boolean updateEnrollmentStatus(Enrollment enrollment, EnrollmentStatus newStatus) {
        for(Enrollment e: enrollments) {
            if(e.getId().equals(enrollment.getId())) {
                e.setStatus(newStatus);
                break;
            } else {
                System.out.println("Enrollment not found for ID: " + enrollment.getId());
                return false;
            }
        }
        return true;
    }

    public List<Enrollment> findEnrollmentByStudent(Student student) {
        List<Enrollment> enrollmentList = new ArrayList<>();
        enrollments.stream().filter(enrollment -> enrollment.getStudent().equals(student)).forEach(enrollmentList::add);
        return enrollmentList;
    }

    public List<Enrollment> findEnrollmentByCourseId(Long courseId) {
        List<Enrollment> enrollmentList = new ArrayList<>();
        enrollments.stream().filter(enrollment -> Objects.equals(enrollment.getCourse().getId(), courseId)).forEach(enrollmentList::add);
        return enrollmentList;
    }

    public Enrollment getEnrollmentById(Long enrollmentId) {
        return enrollments.stream().filter(enrollment -> enrollment.getId().equals(enrollmentId)).findAny().orElse(null);
    }
}
