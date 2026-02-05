package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.ICourseService;
import com.airtribe.learntrack.service.IEnrollmentService;
import com.airtribe.learntrack.service.IStudentService;
import com.airtribe.learntrack.utils.FactoryService;
import com.airtribe.learntrack.utils.InputValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    try {
                        displayMenuForCourse(scanner, factory);
                    } catch (Exception e) {
                        System.out.println("Error in course menu: " + e.getMessage());
                    }
                    break;
                case MenuOptions.Main.MANAGE_STUDENTS:
                    System.out.println("Student management selected.");
                    try {
                        displayMenuForStudent(scanner, factory);
                    } catch (Exception e) {
                        System.out.println("Error in student menu: " + e.getMessage());
                    }
                    break;
                case MenuOptions.Main.MANAGE_ENROLLMENTS:
                    System.out.println("Enrollment management selected.");
                    try {
                        displayMenuForEnrollment(scanner, factory);
                    } catch (Exception e) {
                        System.out.println("Error in enrollment menu: " + e.getMessage());
                    }
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
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nPlease select an option:");
        System.out.println("1. Manage Courses");
        System.out.println("2. Manage Students");
        System.out.println("3. Manage Enrollments");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayMenuCourses() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nCourses Menu:");
        System.out.println("1. Add course");
        System.out.println("2. List courses");
        System.out.println("3. Search Course by Id");
        System.out.println("4. Update Course Details");
        System.out.println("5. Deactivate/Activate a course");
        System.out.println("6. Back to main menu");
        System.out.print("Choice: ");
    }

    private static void displayMenuStudents() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nStudents Menu:");
        System.out.println("1. Add student");
        System.out.println("2. List students");
        System.out.println("3. Search Student by Id");
        System.out.println("4. Update Student Details");
        System.out.println("5. Deactivate a Student");
        System.out.println("6. Back to main menu");
        System.out.print("Choice: ");
    }

    private static void displayEnrollmentInfo() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nEnrollments Menu:");
        System.out.println("1. Enroll student in course");
        System.out.println("2. List enrollments for student");
        System.out.println("3. Mark enrollment status for student");
        System.out.println("4. List all enrollments");
        System.out.println("5. Back to main menu");
        System.out.print("Choice: ");
    }

    private static void displayMenuForCourse(Scanner scanner, FactoryService factory) throws InvalidInputException, EntityNotFoundException {
        boolean returnToMainMenu = false;
        while(!returnToMainMenu) {
            displayMenuCourses();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case MenuOptions.Courses.ADD:
                    System.out.println("Add course selected.");
                    addCourses(scanner, factory);
                    break;
                case MenuOptions.Courses.LIST:
                    System.out.println("List courses selected.");
                    displayCourses(factory);
                    break;

                case MenuOptions.Courses.SEARCH_BY_ID:
                    System.out.println("Search Course By Id.");
                    displayCourseById(factory);
                    break;

                case MenuOptions.Courses.UPDATE:
                    System.out.println("Update Course By Id.");
                    displayCourses(factory);
                    updateCourse(factory);
                    break;
                case MenuOptions.Courses.DEACTIVATE_COURSE:
                    System.out.print("Deactivate/Activate course By Id.");
                    displayCourses(factory);
                    deactivateCourse(scanner, factory);
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

    private static void displayMenuForStudent(Scanner scanner, FactoryService factoryService) throws InvalidInputException, EntityNotFoundException {
        boolean returnToMainMenu = false;
        while(!returnToMainMenu) {
            displayMenuStudents();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case MenuOptions.Students.ADD:
                    System.out.println("Add student selected.");
                    addStudent(scanner, factoryService);
                    break;
                case MenuOptions.Students.LIST:
                    System.out.println("List students selected.");
                    displayStudent(factoryService);
                    break;
                case MenuOptions.Students.SEARCH_BY_ID:
                    System.out.println("Search by Id is selected.");
                    displayStudentById(scanner, factoryService);
                    break;
                case MenuOptions.Students.UPDATE:
                    System.out.println("Update Student Details By Id.");
                    displayStudent(factoryService);
                    updateStudentById(scanner, factoryService);
                    break;
                case MenuOptions.Students.DEACTIVATE_BY_ID:
                    System.out.print("Deactivate/Activate by Id selected.");
                    displayStudent(factoryService);
                    deactivateStudentById(scanner, factoryService);
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

    private static void displayMenuForEnrollment(Scanner scanner, FactoryService factory) throws InvalidInputException, EntityNotFoundException {
        boolean returnToMainMenu = false;
        while(!returnToMainMenu) {
            displayEnrollmentInfo();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case MenuOptions.Enrollments.ENROLL:
                    System.out.println("Enroll student in course selected.");
                    displayStudentAndCourses(factory);
                    enrollStudentInCourse(scanner, factory);
                    break;
                case MenuOptions.Enrollments.LIST_FOR_STUDENT:
                    System.out.println("List enrollments for student selected.");
                    displayStudent(factory);
                    displayEnrollmentByStudent(scanner, factory);
                    break;
                case MenuOptions.Enrollments.Mark_ENROLLMENT_STATUS:
                    System.out.println("Mark enrollment status for student selected.");
                    displayStudent(factory);
                    updateEnrollmentStatus(scanner, factory);
                    break;

                case MenuOptions.Enrollments.LIST_ALL_ENROLLMENTS:
                    System.out.println("Display All Enrollments.");
                    displayAllEnrollments(factory);
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
        System.out.print("\nEnter course name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter course duration in weeks: ");
        String duration = scanner.nextLine().trim();
        Course course = new Course(name, description, duration);
        courseService.addCourse(course);
        System.out.print("Course added successfully.\n");
    }

    private static void displayCourses(FactoryService factory) {
        ICourseService courseService = factory.getCourseService();
        System.out.println("\nAvailable Courses:");
        for (Course course : courseService.getAllCourses()) {
            course.displayCourseInfo();
        }
    }

    private static void deactivateCourse(Scanner scanner, FactoryService factory) throws InvalidInputException {
        ICourseService courseService = factory.getCourseService();
        String input = scanner.nextLine().trim();
        try {
            Long courseId = Long.parseLong(input);
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                System.out.println("Current status: " + (course.isActive() ? "Active" : "Inactive"));
                System.out.print("Do you want to change the status? (yes/no): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();
                if (confirmation.equals("yes")) {
                    boolean newStatus = !course.isActive();
                    if(!newStatus) {
                        removeEnrollmentsForDeactivatedCourse(courseId, factory);
                    }
                    boolean success = courseService.setCourseActiveStatus(courseId, newStatus);
                    if (success) {
                        System.out.println("Course status updated successfully to " + (newStatus ? "Active" : "Inactive") + ".");
                    } else {
                        throw new Exception("Failed to update course status. Please try again.");
                    }
                } else {
                    System.out.println("Course status remains unchanged.");
                }
            }
        }catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid course ID. Please enter a valid number.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
private static void removeEnrollmentsForDeactivatedCourse(Long courseId, FactoryService factory) {
        IEnrollmentService enrollmentService = factory.getEnrollmentService();
        List<Enrollment> enrollments = enrollmentService.findEnrollmentByCourse(courseId);
        for (Enrollment enrollment : enrollments) {
            enrollmentService.setEnrollmentStatus(enrollment, EnrollmentStatus.CANCELLED);
        }
        System.out.println("All enrollments for the deactivated course have been cancelled.");
    }

    private static void addStudent(Scanner scanner, FactoryService factoryService) throws InvalidInputException {
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
            throw new InvalidInputException("Invalid age input. Please enter a valid number.");
        }
    }

    private static void displayStudent(FactoryService factoryService) {
        IStudentService studentService = factoryService.getStudentService();
        System.out.println("\nRegistered Students:");
        for (Student student : studentService.getAllStudents()) {
            System.out.println();
           student.displayInfo();
        }
     }

    private static void displayStudentById(Scanner scanner, FactoryService factoryService) throws InvalidInputException, EntityNotFoundException {
        IStudentService studentService = factoryService.getStudentService();
        System.out.print("Enter student ID to search: ");
        String input = scanner.nextLine().trim();
        try {
            Long studentId = Long.parseLong(input);
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                student.displayInfo();
            } else {
                throw new EntityNotFoundException("Student not found with ID: " + studentId);
            }
        } 
        catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid student ID. Please enter a valid number.");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

     private static void updateStudentById(Scanner scanner, FactoryService factoryService) throws EntityNotFoundException, InvalidInputException {
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
                        throw new InvalidInputException("Invalid age input. Please enter a valid number.");
                    }
                }
                System.out.print("Enter new email (leave blank to keep current): ");
                String email = scanner.nextLine().trim();
                if(!InputValidator.isValidEmail(email)) {
                    throw new InvalidInputException("Invalid email format. Please enter a valid email address.");
                }
                if (!email.isEmpty()) {
                    student.setEmail(email);
                }
                boolean updated = studentService.updateStudent(student);
                if (updated) {
                    System.out.println("Student updated successfully.");
                } else {
                    throw new Exception("Failed to update student. Please try again.");
                }
            } else {
                throw new EntityNotFoundException("Student not found with ID: " + studentId);
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid student ID. Please enter a valid number.");
        } catch(EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     }

    private static void deactivateStudentById(Scanner scanner, FactoryService factoryService) throws InvalidInputException {
        IStudentService studentService = factoryService.getStudentService();
        System.out.println("Enter Student Id: ");
        String input = scanner.nextLine().trim();
        try {
            Long studentId = Long.parseLong(input);
            Student student = studentService.getStudentById(studentId);
            if (student == null) {
                System.out.println("Student not found with ID: " + studentId);
                return;
            }
            System.out.println("Current status: " + (student.isActive() ? "Active" : "Inactive"));
            System.out.print("Do you want to change the status? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("yes")) {
                boolean newStatus = !student.isActive();
                if(!newStatus) {
                    IEnrollmentService enrollmentService = factoryService.getEnrollmentService();
                    List<Enrollment> enrollments = enrollmentService.viewEnrollmentsByStudent(student);
                    for (Enrollment enrollment : enrollments) {
                        enrollmentService.setEnrollmentStatus(enrollment, EnrollmentStatus.CANCELLED);
                    }
                    System.out.println("All enrollments for the deactivated student have been cancelled.");
                }
                boolean success = studentService.setStudentActiveStatus(studentId, newStatus);
                if (success) {
                    System.out.println("Student status updated successfully to " + (newStatus ? "Active" : "Inactive") + ".");
                } else {
                    throw new Exception("Failed to update student status. Please try again.");
                }
            } else {
                System.out.println("Student status remains unchanged.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid student ID. Please enter a valid number.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayCourseById(FactoryService factory) throws EntityNotFoundException, InvalidInputException {
        ICourseService courseService = factory.getCourseService();
        System.out.print("Enter course ID to search: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        try {
            Long courseId = Long.parseLong(input);
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                course.displayCourseInfo();
            } else {
                throw new EntityNotFoundException("Course not found with ID: " + courseId);
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid course ID. Please enter a valid number.");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

     private static void updateCourse(FactoryService factory) throws InvalidInputException, EntityNotFoundException {
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
                    throw new Exception("Failed to update course. Please try again.");
                }
            } else {
                throw new EntityNotFoundException("Course not found with ID: " + courseId);
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid course ID. Please enter a valid number.");
        } catch(EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     }

     private  static void displayStudentAndCourses(FactoryService factory) {
         displayStudent(factory);
         displayCourses(factory);
     }

     private static void enrollStudentInCourse(Scanner scanner, FactoryService factory) throws EntityNotFoundException, InvalidInputException {
        IStudentService studentService = factory.getStudentService();
        ICourseService courseService = factory.getCourseService();
        IEnrollmentService enrollmentService = factory.getEnrollmentService();
        System.out.println("Enrolling Student in Course: ");
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
                int year = LocalDate.now().getYear();
                if(!enrollmentDate.isEmpty()) {
                    LocalDate date = LocalDate.parse(enrollmentDate);
                    year = date.getYear();
                    enrollmentDate = date.toString();
                } else {
                    enrollmentDate = LocalDate.now().toString();
                }
                student.setBatch(year);
                studentService.updateStudent(student);
                enrollmentService.enrollStudentInCourse(student, course, enrollmentDate);
                System.out.println("Student " + student.getName() + " enrolled in course " + course.getCourseName() + " successfully.");
            } else {
                throw new EntityNotFoundException("Invalid student ID or course ID.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input. Please enter valid numbers for student ID and course ID.");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
     }

        private static void displayEnrollmentByStudent(Scanner scanner, FactoryService factory) throws EntityNotFoundException, InvalidInputException {
            IStudentService studentService = factory.getStudentService();
            IEnrollmentService enrollmentService = factory.getEnrollmentService();
            System.out.print("Enter student ID to view enrollments: ");
            String input = scanner.nextLine().trim();
            try {
                Long studentId = Long.parseLong(input);
                Student student = studentService.getStudentById(studentId);
                if (student != null) {
                    List<Enrollment> enrollment = enrollmentService.viewEnrollmentsByStudent(student);
                    if(enrollment.isEmpty()) {
                        throw new EntityNotFoundException("No enrollments found for student: " + student.getName());
                    }
                    System.out.println("\nEnrollments for " + student.getName() + ":");
                    enrollment.forEach(Enrollment::displayEnrollmentDetails);
                } else {
                    throw new EntityNotFoundException("Student not found with ID: " + studentId);
                }
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Invalid student ID. Please enter a valid number.");
            } catch (EntityNotFoundException e) {
                throw new EntityNotFoundException(e.getMessage());
            }
        }

    private static void updateEnrollmentStatus(Scanner scanner, FactoryService factory) throws EntityNotFoundException, InvalidInputException {
        IEnrollmentService enrollmentService = factory.getEnrollmentService();
        IStudentService studentService = factory.getStudentService();
        System.out.print("Enter Student ID to update status: ");
        String input = scanner.nextLine().trim();
        try {
            Long studentId = Long.parseLong(input);
            Student student = studentService.getStudentById(studentId);
            if(student == null) {
                System.out.println("Student not found with ID: " + studentId);
                return;
            }
            List<Enrollment> enrollment = enrollmentService.viewEnrollmentsByStudent(student);
            if (enrollment != null) {
                enrollment.forEach(e -> {
                    try {
                        updateEnrollmentDetails(e, scanner, enrollmentService);
                    } catch (InvalidInputException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            } else {
                throw new EntityNotFoundException("Enrollment not found with student Id: " + student.getName());
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid enrollment ID. Please enter a valid number.");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

     private static void displayAllEnrollments(FactoryService factory) {
         IEnrollmentService enrollmentService = factory.getEnrollmentService();
         List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
         if (enrollments.isEmpty()) {
             System.out.println("No enrollments found.");
         } else {
             System.out.println("\nAll Enrollments:");
             enrollments.forEach(Enrollment::displayEnrollmentDetails);
         }
     }
     private static void updateEnrollmentDetails(Enrollment enrollment, Scanner scanner, IEnrollmentService enrollmentService) throws InvalidInputException {
         System.out.println("Current status: " + enrollment.getStatus());
         System.out.print("Enter new status (ACTIVE, COMPLETED, CANCELLED): ");
         String statusInput = scanner.nextLine().trim().toUpperCase();
         try {
             EnrollmentStatus newStatus = EnrollmentStatus.valueOf(statusInput);
             enrollmentService.setEnrollmentStatus(enrollment, newStatus);
             System.out.println("Enrollment status updated successfully.");
         } catch (IllegalArgumentException e) {
             throw new InvalidInputException("Invalid status. Please enter ACTIVE, COMPLETED, or CANCELLED.");
         }
     }
}