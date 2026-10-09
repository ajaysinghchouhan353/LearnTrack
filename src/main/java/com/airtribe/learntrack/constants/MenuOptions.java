package com.airtribe.learntrack.constants;

public final class MenuOptions {

    private MenuOptions() { /* no-op */ }

    public static final class Main {
        private Main() {}
        public static final String MANAGE_COURSES = "1";
        public static final String MANAGE_STUDENTS = "2";
        public static final String MANAGE_ENROLLMENTS = "3";
        public static final String EXIT = "4";
    }

    public static final class Courses {
        private Courses() {}
        public static final String ADD = "1";
        public static final String LIST = "2";
        public static final String SEARCH_BY_ID = "3";
        public static final String UPDATE = "4";
        public static final String DEACTIVATE_COURSE = "5";
        public static final String BACK = "6";
    }

    public static final class Students {
        private Students() {}
        public static final String ADD = "1";
        public static final String LIST = "2";
        public static final String SEARCH_BY_ID = "3";
        public static final String UPDATE = "4";
        public static final String DEACTIVATE_BY_ID = "5";
        public static final String BACK = "6";
    }

    public static final class Enrollments {
        private Enrollments() {}
        public static final String ENROLL = "1";
        public static final String LIST_FOR_STUDENT = "2";
        public static final String MARK_ENROLLMENT_STATUS = "3";
        public static final String LIST_ALL_ENROLLMENTS = "4";
        public static final String BACK = "5";
    }
}
