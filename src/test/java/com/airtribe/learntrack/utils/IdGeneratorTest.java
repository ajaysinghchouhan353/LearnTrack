package com.airtribe.learntrack.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdGeneratorTest {

    @Test
    public void sequentialIdsIncrease() {
        Long a = IdGenerator.getNextStudentId();
        Long b = IdGenerator.getNextStudentId();
        assertTrue(b > a);
        Long c = IdGenerator.getNextCourseId();
        Long d = IdGenerator.getNextCourseId();
        assertTrue(d > c);
    }
}
