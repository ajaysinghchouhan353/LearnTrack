package com.airtribe.learntrack.utils;

public class IdGenerator {
    private static long studentIdCounter = 0L;
    private static long courseIdCounter = 0L;
    private static long trainerIdCounter = 0L;
    private static long enrollmentIdCounter = 0L;

    public static synchronized Long getNextStudentId() {
        studentIdCounter++;
        return studentIdCounter;
    }

    public static synchronized Long getNextCourseId() {
        courseIdCounter++;
        return courseIdCounter;
    }

    public static synchronized Long getNextTrainerId() {
        trainerIdCounter++;
        return trainerIdCounter;
    }

    public static synchronized Long getNextEnrollmentId() {
        enrollmentIdCounter++;
        return enrollmentIdCounter;
    }
}
