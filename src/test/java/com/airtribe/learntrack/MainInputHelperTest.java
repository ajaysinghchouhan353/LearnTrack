package com.airtribe.learntrack;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Main Input Helper Tests")
class MainInputHelperTest {

    @Test
    @DisplayName("parseIdInput rejects non-numeric student id")
    void testParseIdInputInvalid() {
        InvalidInputException ex = assertThrows(
            InvalidInputException.class,
            () -> Main.parseIdInput("abc", "Student ID")
        );
        assertEquals("Student ID must be a valid number.", ex.getMessage());
    }

    @Test
    @DisplayName("parseIdInput parses numeric value")
    void testParseIdInputValid() throws InvalidInputException {
        Long value = Main.parseIdInput("42", "Student ID");
        assertEquals(42L, value);
    }

    @Test
    @DisplayName("parseEnrollmentDateInput rejects invalid format")
    void testParseEnrollmentDateInvalid() {
        InvalidInputException ex = assertThrows(
            InvalidInputException.class,
            () -> Main.parseEnrollmentDateInput("05-01-2026")
        );
        assertEquals("Enrollment date must be in YYYY-MM-DD format.", ex.getMessage());
    }

    @Test
    @DisplayName("parseEnrollmentDateInput uses current date for blank")
    void testParseEnrollmentDateBlank() throws InvalidInputException {
        LocalDate value = Main.parseEnrollmentDateInput(" ");
        assertEquals(LocalDate.now(), value);
    }

    @Test
    @DisplayName("parseEnrollmentStatusInput rejects invalid status")
    void testParseEnrollmentStatusInvalid() {
        InvalidInputException ex = assertThrows(
            InvalidInputException.class,
            () -> Main.parseEnrollmentStatusInput("paused")
        );
        assertEquals("Status must be ACTIVE, COMPLETED, or CANCELLED.", ex.getMessage());
    }

    @Test
    @DisplayName("parseEnrollmentStatusInput supports case-insensitive value")
    void testParseEnrollmentStatusValid() throws InvalidInputException {
        EnrollmentStatus value = Main.parseEnrollmentStatusInput("completed");
        assertEquals(EnrollmentStatus.COMPLETED, value);
    }

    @Test
    @DisplayName("formatUserError returns consistent context-prefixed message")
    void testFormatUserError() {
        String result = Main.formatUserError("Enrollment Menu", "Student ID must be a valid number.");
        assertTrue(result.startsWith("[Enrollment Menu]"));
        assertEquals("[Enrollment Menu] Student ID must be a valid number.", result);
    }
}
