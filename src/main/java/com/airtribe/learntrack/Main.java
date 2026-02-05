package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.service.ICourseService;
import com.airtribe.learntrack.service.IEnrollmentService;
import com.airtribe.learntrack.service.IStudentService;
import com.airtribe.learntrack.service.Impl.CourseServiceImpl;
import com.airtribe.learntrack.service.Impl.StudentServiceImpl;
import com.airtribe.learntrack.utils.FactoryService;
import com.airtribe.learntrack.utils.InputValidator;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputValidator {
        FactoryService factory = new FactoryService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("-----" + AppConstants.APP_NAME + " v" + AppConstants.VERSION + "-----");
        System.out.printf(AppConstants.WELCOME_MESSAGE + "%n%n");
        while (running) {
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case MenuOptions.Main.MANAGE_COURSES:
                    System.out.println("Course management selected.");
                    displayMenuForCourse(scanner, factory);
                    break;
                case MenuOptions.Main.MANAGE_STUDENTS:
                    System.out.println("Student management selected.");
                    displayMenuForStudent(scanner, factory);
                    break;
                case MenuOptions.Main.MANAGE_ENROLLMENTS:
                    System.out.println("Enrollment management selected.");
                    displayMenuForEnrollment(scanner, factory);
                    break;
                case MenuOptions.Main.EXIT:
                    System.out.println(AppConstants.EXIT_MESSAGE);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");

            }
        }
        scanner.close();
    }
    private static void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Manage Courses");
        System.out.println("2. Manage Students");
        System.out.println("3. Manage Enrollments");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayMenuForCourse(Scanner scanner, FactoryService factory) {
        boolean returnToMainMenu = false;
        while(!returnToMainMenu) {
            System.out.println("\nCourses Menu:");
            System.out.println("1. Add course");
            System.out.println("2. List courses");
            System.out.println("3. Deactivate a course");
            System.out.println("4. Back to main menu");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case MenuOptions.Courses.ADD:
                    System.out.println("Add course selected.");
                    addCourses(scanner, factory);
                    System.out.println("Course added successfully.");
                    break;
                case MenuOptions.Courses.LIST:
                    System.out.println("List courses selected.");
                    displayCourses(factory);
                    System.out.println("Courses listed successfully.");
                    break;

                case MenuOptions.Courses.SEARCH_BY_ID:
                    System.out.println("Update Courses Selected.");
                    displayCourseById(factory);
                    System.out.println("Course listed successfully.");
                    break;

                case MenuOptions.Courses.UPDATE:
                    System.out.println("Update Courses Selected.");
                    updateCourse(factory);
                    System.out.println("Course Updated successfully.");
                    break;
                case MenuOptions.Courses.DEACTIVATE_COURSE:
                    System.out.println("Deactivate course selected.");
                    deactivateCourse(scanner, factory);
                    System.out.println("Course Deactivated successfully.");
                    break;
                case MenuOptions.Courses.BACK:
                    System.out.println("Returning to main menu.");
                    returnToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        }
    }

    private static void displayMenuForStudent(Scanner scanner, FactoryService factoryService) throws InputValidator {
        boolean returnToMainMenu = false;
        while(!returnToMainMenu) {
            System.out.println("\nStudents Menu:");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Search Student by Id");
            System.out.println("4. Update Student Details");
            System.out.println("5. Deactivate a Student");
            System.out.println("6. Back to main menu");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case MenuOptions.Students.ADD:
                    System.out.println("Add student selected.");
                    addStudent(scanner, factoryService);
                    System.out.println("Student added successfully.");
                    break;
                case MenuOptions.Students.LIST:
                    System.out.println("List students selected.");
                    displayStudent(factoryService);
                    System.out.println("Students listed successfully.");
                    break;
                case MenuOptions.Students.SEARCH_BY_ID:
                    System.out.println("Search by Id is selected.");
                    displayStudentById(scanner, factoryService);
                    System.out.println("Student by Id listed successfully.");
                    break;
                case MenuOptions.Students.UPDATE:
                    System.out.println("Update Student Details selected.");
                    updateStudentById(scanner, factoryService);
                    System.out.println("Student Deactivated successfully.");
                    break;
                case MenuOptions.Students.DEACTIVATE_BY_ID:
                    System.out.println("Deactivate by Id selected.");
                    deactivateStudentById(scanner, factoryService);
                    System.out.println("Student Deactivated successfully.");
                    break;
                case MenuOptions.Students.BACK:
                    System.out.println("Returning to main menu.");
                    returnToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenuForEnrollment(Scanner scanner, FactoryService factory) {
        boolean returnToMainMenu = false;
        while(!returnToMainMenu) {
            System.out.println("\nEnrollments Menu:");
            System.out.println("1. Enroll student in course");
            System.out.println("2. List enrollments for student");
            System.out.println("3. Mark Enrollment as Completed/Cancelled");
            System.out.println("4. Back to main menu");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case MenuOptions.Enrollments.ENROLL:
                    System.out.println("Enroll student in course selected.");
                    displayStudentAndCourses(factory);
                    enrollStudentInCourse(scanner, factory);
                    System.out.println("Student enrolled successfully.");
                    break;
                case MenuOptions.Enrollments.LIST_FOR_STUDENT:
                    System.out.println("List enrollments for student selected.");
                    displayStudent(factory);
                    displayEnrollmentByStudent(scanner, factory);
                    System.out.println("Enrollments listed successfully.");
                    break;
                case MenuOptions.Enrollments.Mark_ENROLLMENT_STATUS:
                    System.out.println("Mark enrollment status for student selected.");
                    displayStudent(factory);
                    updateEnrollmentStatus(scanner, factory);
                    System.out.println("Enrollment Status Updated successfully.");
                    break;
                case MenuOptions.Enrollments.BACK:
                    System.out.println("Returning to main menu.");
                    returnToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addCourses(Scanner scanner, FactoryService factory) {
        ICourseService courseService = factory.getCourseService();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter course duration in weeks: ");
        String duration = scanner.nextLine().trim();
        Course course = new Course(name, description, duration);
        courseService.addCourse(course);

    }

    private static void displayCourses(FactoryService factory) {
        ICourseService courseService = factory.getCourseService();
        System.out.println("\nAvailable Courses:");
        for (Course course : courseService.getAllCourses()) {
            System.out.printf("ID: %d | Name: %s | Description: %s | Duration: %s weeks | Active: %b%n",
                    course.getId(), course.getCourseName(), course.getDescription(), course.getDurationInWeeks(), course.isActive());
        }
    }

    private static void deactivateCourse(Scanner scanner, FactoryService factory) {
        ICourseService courseService = factory.getCourseService();
        System.out.print("Enter course ID to deactivate: ");
        String input = scanner.nextLine().trim();
        try {
            Long courseId = Long.parseLong(input);
            boolean success = courseService.setCourseActiveStatus(courseId, false);
            if (success) {
                System.out.println("Course deactivated successfully.");
            } else {
                System.out.println("Course not found or already inactive.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid course ID. Please enter a valid number.");
        }
}

    private static void addStudent(Scanner scanner, FactoryService factoryService) throws InputValidator {
        IStudentService studentService = factoryService.getStudentService();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter student age: ");
        String ageInput = scanner.nextLine().trim();
        try {
            int age = Integer.parseInt(ageInput);
            Student student = new Student(name, age);
            studentService.addStudent(student);
        } catch (NumberFormatException e) {
            throw new InputValidator("Invalid age input. Please enter a valid number.");
        }
    }

    private static void displayStudent(FactoryService factoryService) {
        IStudentService studentService = factoryService.getStudentService();
        System.out.println("\nRegistered Students:");
        for (Student student : studentService.getAllStudents()) {
            System.out.printf("ID: %d | Name: %s | Age: %d | Email: %s | Course Enrolled: %s | Batch: %d | Active: %b%n",
                    student.getStudentID(), student.getName(), student.getAge(), student.getEmail(), student.getCourseEnrolled(), student.getBatch(), student.isActive());
        }
     }

    private static void displayStudentById(Scanner scanner, FactoryService factoryService) throws InputValidator {
        IStudentService studentService = factoryService.getStudentService();
        System.out.print("Enter student ID to search: ");
        String input = scanner.nextLine().trim();
        try {
            Long studentId = Long.parseLong(input);
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                System.out.printf("ID: %d | Name: %s | Age: %d | Email: %s | Course Enrolled: %s | Batch: %d | Active: %b%n",
                        student.getStudentID(), student.getName(), student.getAge(), student.getEmail(), student.getCourseEnrolled(), student.getBatch(), student.isActive());
            } else {
                System.out.println("Student not found with ID: " + studentId);
            }
        } catch (NumberFormatException e) {
            throw new InputValidator("Invalid student ID. Please enter a valid number.");
        }
     }

     private static void updateStudentById(Scanner scanner, FactoryService factoryService) throws InputValidator {
        IStudentService studentService = factoryService.getStudentService();
        System.out.print("Enter student ID to update: ");
        String input = scanner.nextLine().trim();
        try {
            Long studentId = Long.parseLong(input);
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                System.out.print("Enter new name (leave blank to keep current): ");
                String name = scanner.nextLine().trim();
                if (!name.isEmpty()) {
                    student.setName(name);
                }
                System.out.print("Enter new age (leave blank to keep current): ");
                String ageInput = scanner.nextLine().trim();
                if (!ageInput.isEmpty()) {
                    try {
                        int age = Integer.parseInt(ageInput);
                        student.setAge(age);
                    } catch (NumberFormatException e) {
                        throw new InputValidator("Invalid age input. Please enter a valid number.");
                    }
                }
                System.out.print("Enter new email (leave blank to keep current): ");
                String email = scanner.nextLine().trim();
                if (!email.isEmpty()) {
                    student.setEmail(email);
                }
                boolean updated = studentService.updateStudent(student);
                if (updated) {
                    System.out.println("Student updated successfully.");
                } else {
                    System.out.println("Failed to update student. Please try again.");
                }
            } else {
                System.out.println("Student not found with ID: " + studentId);
            }
        } catch (NumberFormatException e) {
            throw new InputValidator("Invalid student ID. Please enter a valid number.");
        }
     }

    private static void deactivateStudentById(Scanner scanner, FactoryService factoryService) throws InputValidator {
        IStudentService studentService = factoryService.getStudentService();
        System.out.print("Enter student ID to deactivate: ");
        String input = scanner.nextLine().trim();
        try {
            Long studentId = Long.parseLong(input);
            boolean success = studentService.setStudentActiveStatus(studentId, false);
            if (success) {
                System.out.println("Student deactivated successfully.");
            } else {
                System.out.println("Student not found or already inactive.");
            }
        } catch (NumberFormatException e) {
            throw new InputValidator("Invalid student ID. Please enter a valid number.");
        }
    }

    private static void displayCourseById(FactoryService factory) {
        ICourseService courseService = factory.getCourseService();
        System.out.print("Enter course ID to search: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        try {
            Long courseId = Long.parseLong(input);
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                System.out.printf("ID: %d | Name: %s | Description: %s | Duration: %s weeks | Active: %b%n",
                        course.getId(), course.getCourseName(), course.getDescription(), course.getDurationInWeeks(), course.isActive());
            } else {
                System.out.println("Course not found with ID: " + courseId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid course ID. Please enter a valid number.");
        }
     }

     private static void updateCourse(FactoryService factory) {
        ICourseService courseService = factory.getCourseService();
        System.out.print("Enter course ID to update: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        try {
            Long courseId = Long.parseLong(input);
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                System.out.print("Enter new name (leave blank to keep current): ");
                String name = scanner.nextLine().trim();
                if (!name.isEmpty()) {
                    course.setCourseName(name);
                }
                System.out.print("Enter new description (leave blank to keep current): ");
                String description = scanner.nextLine().trim();
                if (!description.isEmpty()) {
                    course.setDescription(description);
                }
                System.out.print("Enter new duration in weeks (leave blank to keep current): ");
                String durationInput = scanner.nextLine().trim();
                if (!durationInput.isEmpty()) {
                    course.setDurationInWeeks(durationInput);
                }
                boolean updated = courseService.updateCourse(course);
                if (updated) {
                    System.out.println("Course updated successfully.");
                } else {
                    System.out.println("Failed to update course. Please try again.");
                }
            } else {
                System.out.println("Course not found with ID: " + courseId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid course ID. Please enter a valid number.");
        }
     }

     private  static void displayStudentAndCourses(FactoryService factory) {
         System.out.println("Students List:");
         displayStudent(factory);
         System.out.println("\nCourses List:");
         displayCourses(factory);
     }

     private static void enrollStudentInCourse(Scanner scanner, FactoryService factory) {
        IStudentService studentService = factory.getStudentService();
        ICourseService courseService = factory.getCourseService();
        IEnrollmentService enrollmentService = factory.getEnrollmentService();
        System.out.print("Enter student ID to enroll: ");
        String studentInput = scanner.nextLine().trim();
        System.out.print("Enter course ID to enroll in: ");
        String courseInput = scanner.nextLine().trim();
        try {
            Long studentId = Long.parseLong(studentInput);
            Long courseId = Long.parseLong(courseInput);
            Student student = studentService.getStudentById(studentId);
            Course course = courseService.getCourseById(courseId);
            if (student != null && course != null) {
                System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
                String enrollmentDate = scanner.nextLine().trim();
                if(!enrollmentDate.isEmpty()) {
                    enrollmentDate = LocalDate.parse(enrollmentDate).toString();
                } else {
                    enrollmentDate = java.time.LocalDate.now().toString();
                }
                enrollmentService.enrollStudentInCourse(student, course, enrollmentDate);
                System.out.println("Student " + student.getName() + " enrolled in course " + course.getCourseName() + " successfully.");
            } else {
                System.out.println("Invalid student ID or course ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers for student ID and course ID.");
        }
     }

        private static void displayEnrollmentByStudent(Scanner scanner, FactoryService factory) {
            IStudentService studentService = factory.getStudentService();
            IEnrollmentService enrollmentService = factory.getEnrollmentService();
            System.out.print("Enter student ID to view enrollments: ");
            String input = scanner.nextLine().trim();
            try {
                Long studentId = Long.parseLong(input);
                Student student = studentService.getStudentById(studentId);
                if (student != null) {
                    System.out.println("\nEnrollments for " + student.getName() + ":");
                    enrollmentService.viewEnrollmentsByStudent(student);
                } else {
                    System.out.println("Student not found with ID: " + studentId);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid student ID. Please enter a valid number.");
            }
        }

    private static void updateEnrollmentStatus(Scanner scanner, FactoryService factory) {
        IEnrollmentService enrollmentService = factory.getEnrollmentService();
        IStudentService studentService = factory.getStudentService();
        System.out.print("Enter enrollment ID to update status: ");
        String input = scanner.nextLine().trim();
        try {
            Long studentId = Long.parseLong(input);
            Student student = studentService.getStudentById(studentId);
            Enrollment enrollment = enrollmentService.viewEnrollmentsByStudent(student);
            if (enrollment != null) {
                System.out.println("Current status: " + enrollment.getStatus());
                System.out.print("Enter new status (ACTIVE, COMPLETED, CANCELLED): ");
                String statusInput = scanner.nextLine().trim().toUpperCase();
                try {
                    EnrollmentStatus newStatus = EnrollmentStatus.valueOf(statusInput);
                    enrollmentService.setEnrollmentStatus(enrollment, newStatus);
                    System.out.println("Enrollment status updated successfully.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid status. Please enter ACTIVE, COMPLETED, or CANCELLED.");
                }
            } else {
                System.out.println("Enrollment not found with student Id: " + student.getName());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid enrollment ID. Please enter a valid number.");
        }
     }
}