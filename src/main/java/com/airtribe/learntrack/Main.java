package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    displayMenuForCourse(scanner);
                    break;
                case MenuOptions.Main.MANAGE_STUDENTS:
                    System.out.println("Student management selected.");
                    displayMenuForStudent(scanner);
                    break;
                case MenuOptions.Main.MANAGE_ENROLLMENTS:
                    System.out.println("Enrollment management selected.");
                    displayMenuForEnrollment(scanner);
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
        System.out.println("\n\n\nPlease select an option:");
        System.out.println("1. Manage Courses");
        System.out.println("2. Manage Students");
        System.out.println("3. Manage Enrollments");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayMenuForCourse(Scanner scanner) {
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
                    //function to add Course
                    System.out.println("Course added successfully.");
                    break;
                case MenuOptions.Courses.LIST:
                    System.out.println("List courses selected.");
                    //function to list courses
                    System.out.println("Courses listed successfully.");
                    break;
                case MenuOptions.Courses.DEACTIVATE_COURSE:
                    System.out.println("Deactivate course selected.");
                    //function to list courses
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

    private static void displayMenuForStudent(Scanner scanner) {
        boolean returnToMainMenu = false;
        while(!returnToMainMenu) {
            System.out.println("\nStudents Menu:");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Search Student by Id");
            System.out.println("4. Deactivate a Student");
            System.out.println("3. Back to main menu");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case MenuOptions.Students.ADD:
                    System.out.println("Add student selected.");
                    //function to add Student
                    System.out.println("Student added successfully.");
                    break;
                case MenuOptions.Students.LIST:
                    System.out.println("List students selected.");
                    //function to list students
                    System.out.println("Students listed successfully.");
                    break;
                case MenuOptions.Students.SEARCH_BY_ID:
                    System.out.println("Search by Id is selected.");
                    //function to list students
                    System.out.println("Student by Id listed successfully.");
                    break;
                case MenuOptions.Students.DEACTIVATE_BY_ID:
                    System.out.println("Deactivate by Id selected.");
                    //function to list students
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

    private static void displayMenuForEnrollment(Scanner scanner) {
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
                    //function to enroll student in course
                    System.out.println("Student enrolled successfully.");
                    break;
                case MenuOptions.Enrollments.LIST_FOR_STUDENT:
                    System.out.println("List enrollments for student selected.");
                    //function to list enrollments for student
                    System.out.println("Enrollments listed successfully.");
                    break;
                case MenuOptions.Enrollments.Mark_ENROLLMENT_STATUS:
                    System.out.println("Mark enrollment status for student selected.");
                    //function to list enrollments for student
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
}