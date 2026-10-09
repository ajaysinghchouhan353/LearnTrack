# LearnTrack - Setup Instructions

This document provides comprehensive instructions for setting up and running the LearnTrack Learning Management System.

---

## Table of Contents
1. [System Requirements](#system-requirements)
2. [JDK Version Information](#jdk-version-information)
3. [Installation Steps](#installation-steps)
4. [Running the Application](#running-the-application)
5. [Program Execution Flow](#program-execution-flow)
6. [Troubleshooting](#troubleshooting)

---

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 10/11, macOS 10.14+, or Linux (Ubuntu 18.04+)
- **RAM**: 2 GB minimum (4 GB recommended)
- **Disk Space**: 500 MB free space
- **Java Development Kit (JDK)**: Version 17 or higher
- **Maven**: Version 3.6+ (optional, for building)

### Development Tools (Optional)
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions
- **Terminal/Command Prompt**: For command-line execution

---

## JDK Version Information

### Required JDK Version
**Java Development Kit (JDK) 17** is used for this project.

### JDK Configuration in Project

As specified in `pom.xml`:
```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

### Why Java 17?
- **Long-Term Support (LTS)**: Java 17 is an LTS release with extended support
- **Modern Features**: Includes pattern matching, sealed classes, and enhanced performance
- **Industry Standard**: Widely adopted in enterprise environments
- **Stability**: Mature and stable release suitable for production applications

### Checking Your Java Version

Open a terminal/command prompt and run:
```bash
java -version
```

**Expected Output:**
```
java version "17.0.x" 2023-xx-xx LTS
Java(TM) SE Runtime Environment (build 17.0.x+xx-LTS-xxx)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+xx-LTS-xxx, mixed mode, sharing)
```

If you see version 17 or higher, you're good to go! ✅

### Installing JDK 17

If you don't have JDK 17 installed:

#### **Windows:**
1. Download from [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [OpenJDK 17](https://adoptium.net/)
2. Run the installer (.exe file)
3. Set `JAVA_HOME` environment variable:
   - Right-click "This PC" → Properties → Advanced System Settings
   - Environment Variables → New
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-17`
4. Add to PATH: `%JAVA_HOME%\bin`

#### **macOS:**
```bash
# Using Homebrew
brew install openjdk@17

# Link it
sudo ln -sfn /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk \
     /Library/Java/JavaVirtualMachines/openjdk-17.jdk
```

#### **Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

#### **Verify Installation:**
```bash
java -version
javac -version
```

---

## Installation Steps

### Step 1: Clone the Repository

```bash
git clone https://github.com/ajaysinghchouhan353/LearnTrack.git
cd LearnTrack
```

### Step 2: Verify Project Structure

Ensure the following structure exists:
```
LearnTrack/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── airtribe/
│                   └── learntrack/
│                       ├── Main.java
│                       ├── constants/
│                       ├── entity/
│                       ├── repository/
│                       ├── service/
│                       └── utils/
├── pom.xml
└── README.md
```

### Step 3: Compile the Project

#### **Option A: Using Maven (Recommended)**
```bash
mvn clean compile
```

#### **Option B: Using javac Directly**
```bash
javac -d target/classes -sourcepath src/main/java \
      src/main/java/com/airtribe/learntrack/Main.java
```

---

## Running the Application

### Method 1: Using Maven (Recommended)

```bash
mvn exec:java -Dexec.mainClass="com.airtribe.learntrack.Main"
```

### Method 2: Using java Command

```bash
# First, compile (if not already done)
mvn clean compile

# Then run
java -cp target/classes com.airtribe.learntrack.Main
```

### Method 3: Using IDE

1. Open the project in your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Navigate to `src/main/java/com/airtribe/learntrack/Main.java`
3. Right-click on `Main.java`
4. Select "Run Main.main()"

### Method 4: Creating an Executable JAR

```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/LearnTrack-1.0-SNAPSHOT.jar
```

---

## Program Execution Flow

### Initial Startup Screen

When you run the application, you'll see the welcome screen:

```
-----LearnTrack v1.0.0-----
Welcome to LearnTrack - Your Learning Management System!


Please select an option:
1. Manage Courses
2. Manage Students
3. Manage Enrollments
4. Exit
------------------------------------------------------------------------------
Enter your choice: 
```

### Application Flow Diagram

```
┌─────────────────────────────────────────┐
│         LearnTrack Application          │
│            (Main Menu)                  │
└─────────────────────────────────────────┘
                    │
        ┌───────────┼───────────┐
        │           │           │
        ▼           ▼           ▼
   ┌────────┐  ┌────────┐  ┌──────────┐
   │Courses │  │Students│  │Enrollment│
   │ Menu   │  │  Menu  │  │   Menu   │
   └────────┘  └────────┘  └──────────┘
        │           │           │
        ▼           ▼           ▼
   [CRUD Ops]  [CRUD Ops]  [Enroll/View]
```

### Example Session: "Hello World" Equivalent

Let's walk through a simple session demonstrating the application:

#### **Step 1: Starting the Application**

```
-----LearnTrack v1.0.0-----
Welcome to LearnTrack - Your Learning Management System!


Please select an option:
1. Manage Courses
2. Manage Students
3. Manage Enrollments
4. Exit
------------------------------------------------------------------------------
Enter your choice: 2
```

**Explanation**: User selects option "2" to manage students.

---

#### **Step 2: Adding a Student**

```
----------Student management selected----------

Students Menu:
1. Add student
2. List students
3. Search Student by Id
4. Update Student Details
5. Deactivate a Student
6. Back to main menu
------------------------------------------------------------------------------
Choice: 1
```

User selects option "1" to add a student.

```
----------Add student selected----------
Enter student name: John Doe
Enter student age: 20
Student added successfully.
--------------------------------------------------
```

**Explanation**: 
- Application prompts for student details
- User enters "John Doe" (name) and "20" (age)
- System creates a new Student object with auto-generated ID
- Student is stored in the StudentRepository

**Behind the Scenes:**
```java
// In Main.java
private static void addStudent(Scanner scanner, FactoryService factoryService) {
    System.out.print("Enter student name: ");
    String name = scanner.nextLine().trim();
    System.out.print("Enter student age: ");
    String ageInput = scanner.nextLine().trim();
    int age = Integer.parseInt(ageInput);
    Student student = new Student(name, age);
    studentService.addStudent(student);
}

// In Student.java constructor
public Student(String name, int age) {
    super(name, age);
    this.StudentID = IdGenerator.getNextStudentId(); // Auto-generated ID: 1
}
```

---

#### **Step 3: Listing Students**

```
Students Menu:
1. Add student
2. List students
3. Search Student by Id
4. Update Student Details
5. Deactivate a Student
6. Back to main menu
------------------------------------------------------------------------------
Choice: 2
```

User selects option "2" to list all students.

```
----------List students selected----------

Registered Students:
Name: John Doe
Age: 20
Email: 
Student ID: 1
Batch: 0
Active: true
----------------------------------
```

**Explanation**: 
- Application retrieves all active students from repository
- Displays student information including auto-generated ID
- Shows that the student is active in the system

---

#### **Step 4: Adding a Course**

```
Please select an option:
1. Manage Courses
2. Manage Students
3. Manage Enrollments
4. Exit
------------------------------------------------------------------------------
Enter your choice: 1
```

User navigates to course management.

```
----------Course management selected----------

Courses Menu:
1. Add course
2. List courses
3. Search Course by Id
4. Update Course Details
5. Deactivate/Activate a course
6. Back to main menu
------------------------------------------------------------------------------
Choice: 1
```

User selects option "1" to add a course.

```
----------Add course selected----------
Enter course name: Introduction to Java
Enter course description: Learn Java programming basics
Enter course duration in weeks: 8
Course added successfully.
```

**Explanation**:
- User enters course details
- System creates Course object with auto-generated ID
- Course is stored in CourseRepository

---

#### **Step 5: Enrolling Student in Course**

```
Please select an option:
1. Manage Courses
2. Manage Students
3. Manage Enrollments
4. Exit
------------------------------------------------------------------------------
Enter your choice: 3
```

User navigates to enrollment management.

```
----------Enrollment management selected----------

Enrollments Menu:
1. Enroll student in course
2. List enrollments for student
3. Mark enrollment status for student
4. List all enrollments
5. Back to main menu
------------------------------------------------------------------------------
Choice: 1
```

User selects option "1" to enroll a student.

```
----------Enroll student in course selected----------

Registered Students:
Name: John Doe
Age: 20
Email: 
Student ID: 1
Batch: 0
Active: true
----------------------------------

Available Courses:
Course ID: 1
Course Name: Introduction to Java
Description: Learn Java programming basics
Duration (weeks): 8
Active: true
----------------------------------

Enrolling Student in Course: 
Enter student ID to enroll: 1
Enter course ID to enroll in: 1
Enter Enrollment Date (YYYY-MM-DD): 2026-02-06
Student John Doe enrolled in course Introduction to Java successfully.
```

**Explanation**:
- Application displays all students and courses
- User enters student ID (1) and course ID (1)
- User enters enrollment date
- System creates Enrollment object linking student to course
- Enrollment status set to ACTIVE by default

---

#### **Step 6: Viewing Enrollments**

```
Enrollments Menu:
1. Enroll student in course
2. List enrollments for student
3. Mark enrollment status for student
4. List all enrollments
5. Back to main menu
------------------------------------------------------------------------------
Choice: 4
```

User selects option "4" to view all enrollments.

```
----------Display All Enrollments----------

All Enrollments:
Enrollment ID: 1
Student: John Doe
Course: Introduction to Java
Enrollment Date: 2026-02-06
Status: ACTIVE
```

**Explanation**:
- Application retrieves all enrollments
- Displays enrollment details with relationships
- Shows enrollment status

---

#### **Step 7: Exiting the Application**

```
Please select an option:
1. Manage Courses
2. Manage Students
3. Manage Enrollments
4. Exit
------------------------------------------------------------------------------
Enter your choice: 4
Thank you for using LearnTrack. Goodbye!
```

**Explanation**: 
- User selects option "4" to exit
- Application displays exit message
- Scanner is closed and program terminates gracefully

---

### Key Technical Details

#### Entry Point
```java
// Main.java
public static void main(String[] args) {
    FactoryService factory = new FactoryService();
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    System.out.println("-----" + AppConstants.APP_NAME + " v" + AppConstants.VERSION + "-----");
    System.out.printf(AppConstants.WELCOME_MESSAGE + "%n%n");
    while (running) {
        displayMenu();
        String choice = scanner.nextLine();
        // Handle menu navigation...
    }
    scanner.close();
}
```

#### Constants Used
```java
// AppConstants.java
public static final String APP_NAME = "LearnTrack";
public static final String VERSION = "1.0.0";
public static final String WELCOME_MESSAGE = "Welcome to LearnTrack - Your Learning Management System!";
public static final String EXIT_MESSAGE = "Thank you for using LearnTrack. Goodbye!";
```

#### Data Flow
1. **User Input** → Scanner captures console input
2. **Menu Selection** → Switch statement routes to appropriate handler
3. **Service Layer** → Business logic processes request
4. **Repository Layer** → Data stored in ArrayList collections
5. **Output** → Results displayed via System.out.println()

---

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: "java: invalid source release 17"
**Cause**: JDK version mismatch or incorrect Maven configuration.

**Solution**:
```bash
# Check Java version
java -version
javac -version

# If using Maven, clean and recompile
mvn clean compile -X
```

#### Issue 2: "Error: Could not find or load main class"
**Cause**: Incorrect classpath or missing compiled classes.

**Solution**:
```bash
# Recompile the project
mvn clean compile

# Run with correct classpath
java -cp target/classes com.airtribe.learntrack.Main
```

#### Issue 3: "java.util.NoSuchElementException"
**Cause**: Scanner trying to read when no input is available.

**Solution**: Ensure you're running in an interactive terminal, not as a background process.

#### Issue 4: "NumberFormatException: For input string"
**Cause**: Invalid input when number is expected.

**Solution**: The application has input validation. If you see this, ensure you're entering valid numbers when prompted for IDs, ages, etc.

#### Issue 5: Maven not found
**Cause**: Maven is not installed or not in PATH.

**Solution**:
```bash
# Install Maven (macOS)
brew install maven

# Install Maven (Ubuntu/Debian)
sudo apt install maven

# Install Maven (Windows)
# Download from https://maven.apache.org/download.cgi
# Add to PATH: C:\apache-maven-3.x.x\bin
```

### Getting Help

If you encounter issues:

1. **Check Java Version**: Ensure JDK 17 is installed and active
2. **Clean Build**: Run `mvn clean compile` to rebuild
3. **Check Logs**: Review console output for error messages
4. **Verify Structure**: Ensure all source files are in correct directories
5. **GitHub Issues**: Report bugs at [https://github.com/ajaysinghchouhan353/LearnTrack/issues](https://github.com/ajaysinghchouhan353/LearnTrack/issues)

---

## Performance Notes

### Expected Behavior
- **Startup Time**: < 1 second
- **Response Time**: Instant for all operations (in-memory storage)
- **Memory Usage**: ~50-100 MB (depending on data volume)

### Data Persistence
⚠️ **Important**: All data is stored in-memory using ArrayList. Data will be lost when the application terminates.

For persistent storage, consider integrating:
- File-based storage (JSON, XML)
- Database (MySQL, PostgreSQL, H2)
- Serialization

---

## Additional Resources

- **Java 17 Documentation**: [https://docs.oracle.com/en/java/javase/17/](https://docs.oracle.com/en/java/javase/17/)
- **Maven Documentation**: [https://maven.apache.org/guides/](https://maven.apache.org/guides/)
- **GitHub Repository**: [https://github.com/ajaysinghchouhan353/LearnTrack](https://github.com/ajaysinghchouhan353/LearnTrack)

---

## Summary

✅ **JDK 17** is required and configured in `pom.xml`  
✅ Application starts with a **welcome message** and **interactive menu**  
✅ Users can manage **Students**, **Courses**, and **Enrollments**  
✅ All operations are **console-based** with clear prompts  
✅ Data is stored **in-memory** for quick access  
✅ Application exits gracefully with a **goodbye message**

---

**Document Version**: 1.0  
**Last Updated**: 2026-02-06  
**Author**: AirTribe Team  
**Tested On**: JDK 17.0.9, Maven 3.9.5, macOS 14 / Windows 11 / Ubuntu 22.04