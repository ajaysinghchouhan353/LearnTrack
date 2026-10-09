package com.airtribe.learntrack.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {

    @Test
    public void emailValidation() {
        assertFalse(InputValidator.isValidEmail(null));
        assertFalse(InputValidator.isValidEmail(""));
        assertFalse(InputValidator.isValidEmail("not-an-email"));
        assertTrue(InputValidator.isValidEmail("user@example.com"));
    }
}
