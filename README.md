# LearnTrack

A console-based Student and Course Management System built with Java 17 and Maven.

## Overview

LearnTrack is a menu-driven application for managing students, courses, and course enrollments. The system supports:
- Adding and managing students (active/inactive status)
- Creating and maintaining courses
- Enrolling students in courses with status tracking
- Updating enrollment status (ACTIVE, COMPLETED, CANCELLED)
- Deactivating courses and viewing enrollment history

## Project Architecture

```
src/main/java/com/airtribe/learntrack/
├── Main.java                          # Entry point, menu-driven UI
├── entity/                            # Domain models
│   ├── Course.java                   # Course entity with equals/hashCode
│   ├── Student.java                  # Student entity (extends Person)
│   ├── Enrollment.java               # Enrollment join entity with status
│   ├── Person.java                   # Base class for Student
│   └── Trainer.java
├── repository/                        # In-memory data access layer
│   ├── CourseRepository.java
│   ├── StudentRepository.java
│   └── EnrollmentRepository.java
├── service/                           # Business logic layer
│   ├── ICourseService.java
│   ├── IStudentService.java
│   ├── IEnrollmentService.java
│   └── Impl/
│       ├── CourseServiceImpl.java
│       ├── StudentServiceImpl.java
│       └── EnrollmentServiceImpl.java
├── exception/                         # Custom exceptions
│   ├── EntityNotFoundException.java
│   └── InvalidInputException.java
├── constants/                         # App configuration
│   ├── AppConstants.java
│   └── MenuOptions.java
├── enums/                             # Application enums
│   └── EnrollmentStatus.java
└── utils/                             # Utility classes
    ├── IdGenerator.java              # Synchronized ID generation
    ├── InputValidator.java           # Email validation
    └── FactoryService.java           # Service factory/DI
```

## Build and Run

### Prerequisites
- Java 17 (Amazon Corretto or equivalent)
- Maven 3.8+

### Build
```bash
mvn clean package
```

### Run Interactive Mode
```bash
mvn exec:java -Dexec.mainClass="com.airtribe.learntrack.Main"
```

Or directly:
```bash
java -cp target/classes com.airtribe.learntrack.Main
```

### Run Tests
```bash
mvn test
```

## Core Features

### Student Management
- Add student (name, email, phone)
- Retrieve student details by ID
- View all active/inactive students
- Deactivate student account

### Course Management
- Create course (name, description, duration)
- Update course details
- Retrieve course information
- Deactivate course

### Enrollment Management
- Enroll student in course
- Track enrollment status (ACTIVE/COMPLETED)
- Update enrollment status
- View enrollments by student or course

## Technical Stack

- **Language:** Java 17
- **Build Tool:** Maven
- **Testing:** JUnit 5 (Jupiter)
- **Logging:** SLF4J with Simple Binding
- **Persistence:** In-memory ArrayList repositories
- **ID Generation:** Thread-safe synchronized counters

## Key Implementation Details

### Error Handling
Services throw `EntityNotFoundException` for missing entities and `InvalidInputException` for invalid inputs.

### Defensive Programming
- Repositories return defensive copies of lists to prevent external mutation
- Null-safe email validation
- Synchronized ID generation with private counters

### Entity Equality
All entities (Student, Course, Enrollment) implement `equals()` and `hashCode()` based on their ID fields.

### Logging
Uses SLF4J facade with Simple binding. Main flow logs include MDC context (`sessionId`, `menu`) for better traceability.

## Test Coverage

| Component | Tests | Status |
|-----------|-------|--------|
| Main Input Helper Tests | 7 | ✓ Pass |
| Integration Layer | 7 | ✓ Pass |
| Repository Layer | 2 | ✓ Pass |
| Service Layer | 3 | ✓ Pass |
| Utility Layer | 2 | ✓ Pass |
| **Total** | **21** | **✓ Pass** |

## Menu Options

```
Main Menu
1. Manage Courses
2. Manage Students
3. Manage Enrollments
4. Exit

Courses Menu
1. Add course
2. List courses
3. Search course by ID
4. Update course details
5. Deactivate/Activate course
6. Back to main menu

Students Menu
1. Add student
2. List students
3. Search student by ID
4. Update student details
5. Deactivate/Activate student
6. Back to main menu

Enrollments Menu
1. Enroll student in course
2. List enrollments for student
3. Mark enrollment status
4. List all enrollments
5. Back to main menu
```

## Usage Example

```
--- Welcome to LearnTrack ---
Choose an option:
1. Add Student
2. Get Student Details
...
> 1
Enter student name: John Doe
Enter email: john@example.com
Enter phone: 9876543210
Student added successfully with ID: 1

...
> 10
Enter student ID: 1
Enter course ID: 1
Enrolled successfully with ID: 1
```

## Status

✓ **Build:** Passing  
✓ **Tests:** 21/21 passing  
✓ **Core Features:** Implemented  
✓ **Logging:** Integrated (SLF4J + MDC context)

## Known Limitations

- In-memory storage (data lost on application exit)
- Single-threaded console UI
- No persistence layer (database integration not implemented)
