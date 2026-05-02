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
        return new java.util.ArrayList<>(enrollments);
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public boolean updateEnrollmentStatus(Enrollment enrollment, EnrollmentStatus newStatus) {
        boolean found = false;
        for (Enrollment e : enrollments) {
            if (e.getId().equals(enrollment.getId())) {
                e.setStatus(newStatus);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Enrollment not found for ID: " + enrollment.getId());
        }
        return found;
    }

    public List<Enrollment> findEnrollmentByStudent(Student student) {
        List<Enrollment> enrollmentList = new ArrayList<>();
        Long sid = student != null ? student.getStudentId() : null;
        if (sid != null) {
            enrollments.stream()
                    .filter(enrollment -> enrollment.getStudent() != null && sid.equals(enrollment.getStudent().getStudentId()))
                    .forEach(enrollmentList::add);
        }
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
