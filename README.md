# LearnTrack

LearnTrack is a Java-based learning management console application for managing students, courses, and enrollments. The project follows a layered architecture with entities, repositories, service interfaces, and concrete implementations, and it is built with Java 17 and Maven.

## Overview

This application allows an educational institution to:
- add, list, search, update, and deactivate students
- add, list, search, update, and deactivate courses
- enroll students in courses with enrollment dates
- update enrollment status such as ACTIVE, COMPLETED, and CANCELLED
- validate input and prevent invalid or inconsistent operations
- demonstrate the full workflow through a non-interactive demo runner

## Features

### Student management
- create students with name, age, and optional email
- assign and update batch information
- list active and inactive students
- search a student by ID
- update student details
- deactivate or reactivate a student
- maintain enrollment consistency when a student is toggled off

### Course management
- create courses with name, description, and duration in weeks
- list active and inactive courses
- search a course by ID
- update course details
- deactivate or reactivate a course
- maintain enrollment cancellation when a course is deactivated

### Enrollment management
- enroll a student in a course on a specific date
- view enrollments by student
- view enrollments by course
- list all enrollments
- update enrollment status
- cancel enrollments linked to inactive students or inactive courses

### Validation and business rules
- email validation using a basic regex-based checker
- invalid numeric and date inputs are rejected with user-friendly errors
- inactive students and courses cannot be enrolled into
- enrollment status changes are tracked in the repository layer

## Demo runner

The project includes a demo file that exercises the system end-to-end:

- course setup and updates
- student creation and updates
- enrollment creation and status changes
- deactivation and reactivation flows
- final system snapshot

Run the demo from the project root with PowerShell:

```powershell
mvn --% exec:java -Dexec.mainClass=com.airtribe.learntrack.DemoRunner
```

Or with the classpath approach if needed:

```powershell
java -cp "target/classes;$(Get-Content -Raw cp.txt)" com.airtribe.learntrack.DemoRunner
```

## Getting started

### Prerequisites
- JDK 17+
- Maven 3.6+

### Build the project

```bash
mvn clean test
```

### Run the interactive app

```powershell
mvn --% exec:java -Dexec.mainClass=com.airtribe.learntrack.Main
```

## Project structure

```text
LearnTrack/
├── pom.xml
├── cp.txt
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── airtribe/
│   │   │           └── learntrack/
│   │   │               ├── DemoRunner.java
│   │   │               ├── Main.java
│   │   │               ├── constants/
│   │   │               ├── entity/
│   │   │               ├── enums/
│   │   │               ├── exception/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               └── utils/
│   │   └── resources/
│   │       ├── config.properties
│   │       └── docs/
│   └── test/
│       └── java/
│           └── com/
│               └── airtribe/
│                   └── learntrack/
└── target/
```

## Main menu flow

When the app starts, it shows a menu similar to:

```text
Welcome to LearnTrack - Your Learning Management System!

1. Manage Courses
2. Manage Students
3. Manage Enrollments
4. Exit
```

### Course menu
- add course
- list courses
- search course by ID
- update course details
- deactivate a course
- return to main menu

### Student menu
- add student
- list students
- search student by ID
- update student details
- deactivate a student
- return to main menu

### Enrollment menu
- enroll student in course
- view student enrollments
- update enrollment status
- view all enrollments
- return to main menu

## Enrollment status values

The application uses the following enrollment statuses:
- ACTIVE
- COMPLETED
- CANCELLED

## Tech stack
- Java 17
- Maven
- JUnit 5
- SLF4J logging

## Notes

This project is designed as a console-based learning project and is intentionally in-memory, not backed by a database. It is best suited for understanding Java OOP, collections, validation, service/repository separation, and basic console application structure.

## Status

The project is functionally complete for the implemented LearnTrack brief and verified by passing tests and a successful demo run.
