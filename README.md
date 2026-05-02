# LearnTrack

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Project-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

> **LearnTrack** - Your comprehensive Learning Management System for managing students, courses, and enrollments.

## 📖 Overview

LearnTrack is a console-based Student and Course Management System built with Java. It provides a complete solution for educational institutions to manage their students, courses, and enrollment processes efficiently. The application follows a clean architecture with separation of concerns using repositories, services, and entities.

## ✨ Features

### 👨‍🎓 Student Management
- **Add Students**: Register new students with personal information
- **List Students**: View all active students in the system
- **Search Students**: Find students by their unique ID
- **Update Students**: Modify student details
- **Deactivate Students**: Soft delete students from the system

### 📚 Course Management
- **Add Courses**: Create new courses with details
- **List Courses**: Display all available courses
- **Search Courses**: Find courses by ID
- **Update Courses**: Modify course information
- **Deactivate Courses**: Remove courses from active listings

### 📝 Enrollment Management
- **Enroll Students**: Register students in courses
- **View Enrollments**: List all enrollments or filter by student
- **Update Status**: Mark enrollment status (Active, Completed, Dropped, etc.)
- **Track Progress**: Monitor student enrollment history

## 🏗️ Project Structure

```
LearnTrack/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── airtribe/
│                   └── learntrack/
│                       ├── Main.java                    # Application entry point
│                       ├── constants/
│                       │   ├── AppConstants.java        # Application constants
│                       │   └── MenuOptions.java         # Menu option constants
│                       ├── entity/
│                       │   ├── Person.java              # Base person entity
│                       │   ├── Student.java             # Student entity
│                       │   ├── Course.java              # Course entity
│                       │   └── Enrollment.java          # Enrollment entity
│                       ├── enums/
│                       │   └── EnrollmentStatus.java    # Enrollment status enum
│                       ├── exception/
│                       │   ├── EntityNotFoundException.java
│                       │   └── InvalidInputException.java
│                       ├── repository/
│                       │   ├── StudentRepository.java   # Student data access
│                       │   ├── CourseRepository.java    # Course data access
│                       │   └── EnrollmentRepository.java # Enrollment data access
│                       ├── service/
│                       │   ├── IStudentService.java     # Student service interface
│                       │   ├── ICourseService.java      # Course service interface
│                       │   ├── IEnrollmentService.java  # Enrollment service interface
│                       │   └── Impl/
│                       │       ├── StudentServiceImpl.java
│                       │       ├── CourseServiceImpl.java
│                       │       └── EnrollmentServiceImpl.java
│                       └── utils/
│                           ├── IdGenerator.java         # ID generation utility
│                           ├── InputValidator.java      # Input validation utility
│                           └── FactoryService.java      # Service factory
├── pom.xml
└── README.md
```

## 🛠️ Technology Stack

- **Language**: Java 17
- **Build Tool**: Maven
- **Architecture**: Layered Architecture (Entity, Repository, Service, Controller)
- **Design Patterns**: 
  - Repository Pattern
  - Service Layer Pattern
  - Factory Pattern
  - Singleton Pattern

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/ajaysinghchouhan353/LearnTrack.git
   cd LearnTrack
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.airtribe.learntrack.Main"
   ```

   Or compile and run directly:
   ```bash
   javac -d target/classes src/main/java/com/airtribe/learntrack/Main.java
   java -cp target/classes com.airtribe.learntrack.Main
   ```

## 📋 Usage

Upon running the application, you'll be presented with the main menu:

```
Welcome to LearnTrack - Your Learning Management System!

Main Menu:
1. Manage Courses
2. Manage Students
3. Manage Enrollments
4. Exit
```

### Managing Students

```
Students Menu:
1. Add student
2. List students
3. Search Student by Id
4. Update Student Details
5. Deactivate a Student
6. Back to main menu
```

### Managing Courses

```
Courses Menu:
1. Add course
2. List courses
3. Search Course by Id
4. Update Course Details
5. Deactivate a Course
6. Back to main menu
```

### Managing Enrollments

```
Enrollments Menu:
1. Enroll student in course
2. List enrollments for student
3. Mark enrollment status for student
4. List all enrollments
5. Back to main menu
```

## 🔑 Key Components

### Entities
- **Person**: Base class with common attributes (name, email, phone)
- **Student**: Extends Person, includes student-specific attributes
- **Course**: Represents a course with title, description, and status
- **Enrollment**: Links students to courses with enrollment details

### Services
- **StudentService**: Business logic for student operations
- **CourseService**: Business logic for course operations
- **EnrollmentService**: Business logic for enrollment operations

### Repositories
- **StudentRepository**: Data access layer for students
- **CourseRepository**: Data access layer for courses
- **EnrollmentRepository**: Data access layer for enrollments

### Utilities
- **IdGenerator**: Generates unique IDs for entities
- **InputValidator**: Validates user inputs
- **FactoryService**: Creates and manages service instances

## 🎯 Enrollment Status

The system supports various enrollment statuses:
- **ACTIVE**: Student is currently enrolled

## Class Diagram
classDiagram
    %% Entity Classes
    class Person {
        -String firstName
        -String lastName
        -int age
        -String email
        +Person()
        +Person(String name, int age)
        +Person(String name, int age, String email)
        +setFirstAndLastName(String name)
        +getName() String
        +getAge() int
        +setAge(int age)
        +getEmail() String
        +setEmail(String email)
        +displayInfo() void
    }

    class Student {
        -Long StudentID
        -int batch
        -boolean active
        +Student()
        +Student(String name, int age)
        +Student(String name, int age, String email)
        +setName(String name)
        +getStudentID() Long
        +setBatch(int batch)
        +isActive() boolean
        +setActive(boolean active)
        +displayInfo() void
    }

    class Course {
        -Long id
        -String courseName
        -String description
        -String duration
        -boolean active
        +Course(String courseName, String description, String duration)
        +getId() Long
        +getCourseName() String
        +setCourseName(String courseName)
        +getDescription() String
        +setDescription(String description)
        +getDuration() String
        +setDuration(String duration)
        +isActive() boolean
        +setActive(boolean active)
        +displayCourseInfo() void
    }

    class Enrollment {
        -Long id
        -Student student
        -Course course
        -String enrollmentDate
        -EnrollmentStatus status
        +Enrollment(Student student, Course course, String enrollmentDate)
        +getId() Long
        +getStudent() Student
        +getCourse() Course
        +getStatus() EnrollmentStatus
        +setStatus(EnrollmentStatus status)
        +displayEnrollmentDetails() void
    }

    %% Enum
    class EnrollmentStatus {
        <<enumeration>>
        ACTIVE
        COMPLETED
        DROPPED
        PENDING
    }

    %% Repository Classes
    class StudentRepository {
        -List~Student~ students
        +addStudent(Student student)
        +getStudents() List~Student~
        +getDisabledStudents() List~Student~
        +getStudentById(Long studentId) Student
        +updateStudentStatus(Long studentId, boolean status) boolean
        +updateStudent(Student student) boolean
    }

    class CourseRepository {
        -List~Course~ courses
        +addCourse(Course course)
        +getAllCourses() List~Course~
        +getAllDisabledCourses() List~Course~
        +getCourseById(Long courseId) Course
        +updateCourse(Course course) boolean
        +updateCourseStatus(Long courseId, boolean status) boolean
    }

    class EnrollmentRepository {
        -List~Enrollment~ enrollments
        +getAllEnrollments() List~Enrollment~
        +addEnrollment(Enrollment enrollment)
        +updateEnrollmentStatus(Enrollment enrollment, EnrollmentStatus newStatus)
        +findEnrollmentByStudent(Student student) List~Enrollment~
        +findEnrollmentByCourseId(Long courseId) List~Enrollment~
        +getEnrollmentById(Long enrollmentId) Enrollment
    }

    %% Service Interfaces
    class IStudentService {
        <<interface>>
        +addStudent(Student student)
        +getAllStudents() List~Student~
        +getAllDisabledStudents() List~Student~
        +getStudentById(Long studentId) Student
        +updateStudent(Student student) boolean
        +updateStudentStatus(Long studentId, boolean status) boolean
    }

    class ICourseService {
        <<interface>>
        +addCourse(Course course)
        +getAllCourses() List~Course~
        +getAllDisabledCourses() List~Course~
        +getCourseById(Long courseId) Course
        +updateCourse(Course course) boolean
        +updateCourseStatus(Long courseId, boolean status) boolean
    }

    class IEnrollmentService {
        <<interface>>
        +enrollStudentInCourse(Student student, Course course, String enrollmentDate)
        +viewEnrollmentsByStudent(Student student) List~Enrollment~
        +setEnrollmentStatus(Enrollment enrollment, EnrollmentStatus status)
        +findEnrollmentByCourse(Long courseId) List~Enrollment~
        +getAllEnrollments() List~Enrollment~
        +getEnrollmentById(Long enrollmentId) Enrollment
    }

    %% Service Implementations
    class StudentServiceImpl {
        -StudentRepository studentRepository
        +StudentServiceImpl()
    }

    class CourseServiceImpl {
        -CourseRepository courseRepository
        +CourseServiceImpl()
    }

    class EnrollmentServiceImpl {
        -EnrollmentRepository enrollmentRepository
        +EnrollmentServiceImpl()
    }

    %% Factory and Main
    class FactoryService {
        -ICourseService courseService
        -IStudentService studentService
        -IEnrollmentService enrollmentService
        +FactoryService()
        +getCourseService() ICourseService
        +getStudentService() IStudentService
        +getEnrollmentService() IEnrollmentService
    }

    class Main {
        +main(String[] args)
        -displayMenu()
        -displayMenuCourses()
        -displayMenuStudents()
        -displayEnrollmentInfo()
        +various private helper methods
    }

    %% Relationships
    Student --|> Person : extends
    Enrollment --> Student : has
    Enrollment --> Course : has
    Enrollment --> EnrollmentStatus : uses

    StudentRepository --> Student : manages
    CourseRepository --> Course : manages
    EnrollmentRepository --> Enrollment : manages

    IStudentService <|.. StudentServiceImpl : implements
    ICourseService <|.. CourseServiceImpl : implements
    IEnrollmentService <|.. EnrollmentServiceImpl : implements

    StudentServiceImpl --> StudentRepository : uses
    CourseServiceImpl --> CourseRepository : uses
    EnrollmentServiceImpl --> EnrollmentRepository : uses

    FactoryService --> ICourseService : creates
    FactoryService --> IStudentService : creates
    FactoryService --> IEnrollmentService : creates

    Main --> FactoryService : uses
