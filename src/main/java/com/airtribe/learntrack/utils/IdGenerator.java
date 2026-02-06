package com.airtribe.learntrack.utils;

public class IdGenerator {
    public static Long studentIdCounter;
    public static Long courseIdCounter;
    public static Long trainerIdCounter;
    public static Long enrollmentIdCounter;

    public static Long getNextStudentId() {
        if (studentIdCounter == null) {
            studentIdCounter = 1L;
        } else {
            studentIdCounter++;
        }
        return studentIdCounter;
    }

    public static Long getNextCourseId() {
        if (courseIdCounter == null) {
            courseIdCounter = 1L;
        } else {
            courseIdCounter++;
        }
        return courseIdCounter;
    }

    public static Long getNextTrainerId() {
        if (trainerIdCounter == null) {
            trainerIdCounter = 1L;
        } else {
            trainerIdCounter++;
        }
        return trainerIdCounter;
    }

    public static Long getNextEnrollmentId() {
        if (enrollmentIdCounter == null) {
            enrollmentIdCounter = 1L;
        } else {
            enrollmentIdCounter++;
        }
        return enrollmentIdCounter;
    }
}
