package com.airtribe.learntrack.utils;

public class InputValidator{
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        email = email.trim();
        if (email.isEmpty()) {
            return false;
        }
        // Simple regex for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
