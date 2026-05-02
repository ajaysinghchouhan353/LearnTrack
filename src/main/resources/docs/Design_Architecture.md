# LearnTrack - Architecture & Design Notes

## System Architecture

LearnTrack follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────┐
│          User Interface (UI)            │  Main.java - Menu-driven console
├─────────────────────────────────────────┤
│       Service Layer (Business Logic)    │  ICourseService, IStudentService, IEnrollmentService
├─────────────────────────────────────────┤
│        Repository Layer (Data Access)   │  Repositories manage in-memory storage
├─────────────────────────────────────────┤
│         Domain Entities (Models)        │  Student, Course, Enrollment, Person
└─────────────────────────────────────────┘
```

## Component Overview

### 1. Entity Layer (`entity/` package)

**Purpose:** Represents domain models

#### Student (extends Person)
- **Attributes:** studentId, name, email, phone, age, batch, active
- **Responsibilities:** Encapsulate student data
- **Key Methods:** displayInfo(), equals(), hashCode()
- **Status Management:** Can be marked active/inactive

#### Course
- **Attributes:** id, courseName, description, durationInWeeks, active
- **Responsibilities:** Encapsulate course data
- **Key Methods:** displayCourseInfo(), equals(), hashCode()
- **Getter Added:** getDurationInWeeks() for UI display

#### Enrollment (Join Entity)
- **Attributes:** id, student, course, enrollmentDate, status
- **Relationships:** Links Student ↔ Course with status tracking
- **Status Values:** ACTIVE, COMPLETED, CANCELLED
- **Key Methods:** displayEnrollmentDetails(), equals(), hashCode()

#### Person (Base Class)
- **Abstract:** Serves as base for Student
- **Attributes:** name, email, phone, age
- **Inheritance:** Student extends Person

### 2. Repository Layer (`repository/` package)

**Pattern:** Data Access Object (DAO)  
**Storage:** In-memory ArrayLists (no database)  
**Key Feature:** Returns defensive copies to prevent external mutation

#### StudentRepository
```java
Methods:
- addStudent(Student): void
- getStudentById(Long): Student  // Returns regardless of active flag
- updateStudent(Student): void
- getStudents(): List<Student>   // Active students only
- getDisabledStudents(): List<Student>  // Inactive students
```

#### CourseRepository
```java
Methods:
- addCourse(Course): void
- getCourseById(Long): Course   // Returns regardless of active flag
- updateCourse(Course): void
- getAllCourses(): List<Course>  // Active courses only
- getAllDisabledCourses(): List<Course>  // Inactive courses
```

#### EnrollmentRepository
```java
Methods:
- addEnrollment(Enrollment): void
- findEnrollmentByStudent(Student): List<Enrollment>
- findEnrollmentByCourse(Long): List<Enrollment>
- updateEnrollmentStatus(Long, EnrollmentStatus): void
- getEnrollmentById(Long): Enrollment
- getAllEnrollments(): List<Enrollment>
- Optimization: Compares by studentId in lookups (not object identity)
```

**Design Decisions:**
- Repositories return **defensive copies** via `new ArrayList<>(internalList)`
- getById methods return entities **regardless of active flag** (for UI flexibility)
- Status/list getters respect active flag (won't show inactive in lists)

### 3. Service Layer (`service/` package)

**Pattern:** Façade + Business Logic  
**Exception Handling:** Throws `EntityNotFoundException` and `InvalidInputException`

#### IStudentService & StudentServiceImpl
```
Responsibilities:
- Validate student data through InputValidator
- Coordinate with StudentRepository
- Handle business rules (deactivation cascades to enrollments)
- Throw EntityNotFoundException when student not found
```

#### ICourseService & CourseServiceImpl
```
Responsibilities:
- Validate course parameters
- Manage course deactivation logic
- Coordinate with CourseRepository
- Throw EntityNotFoundException when course not found
```

#### IEnrollmentService & EnrollmentServiceImpl
```
Responsibilities:
- Enroll students in courses with date tracking
- Change enrollment status
- Query enrollments by student/course
- Handle status update validation
- Ensure student and course exist before enrollment
```

**API Changes Made:**
- Methods now throw `EntityNotFoundException` instead of returning boolean
- Provides better error reporting and client-side error handling

### 4. Utility Layer (`utils/` package)

#### IdGenerator
- **Singleton Pattern:** Static counter management
- **Thread-Safety:** Synchronized increment methods
- **Design:** Private counters for each entity type
- **Methods:**
  - getNextStudentId(): Long
  - getNextCourseId(): Long  
  - getNextEnrollmentId(): Long

#### InputValidator
- **Email Validation:** Regex-based with null-safety
- **Defensive:** Trims input and checks for null/empty
- **Reusable:** Static methods for validation

#### FactoryService
- **Dependency Injection:** Creates and provides service instances
- **Singleton:** Single factory instance in Main
- **Methods:** getStudentService(), getCourseService(), getEnrollmentService()

### 5. Exception Hierarchy (`exception/` package)

#### EntityNotFoundException
- Thrown when entity lookup fails
- Helps distinguish from programming errors
- Caught and handled at UI layer

#### InvalidInputException
- Thrown when validation fails
- Provides specific error messages
- Used for form validation feedback

### 6. UI Layer - Main.java

**Architecture:**
- **Single Scanner Instance:** Passed through all methods to avoid resource leaks
- **Menu Loop:** Hierarchical menu structure (Main → Courses/Students/Enrollments)
- **Error Handling:** Try-catch-finally in all menu loops
- **Logging:** All operations logged at INFO/WARN/ERROR levels (prepared for enhancement)

**Flow Example - Add Student:**
```
Main Menu
  → Students Menu
    → Add Student
      → Prompt for name, age
      → Validate inputs
      → Create Student object
      → Call StudentServiceImpl.addStudent()
      → Log success or error
      → Return to Students Menu
```

**Exception Handling:**
- User-facing errors logged as WARN
- System errors logged as ERROR
- Operation-level try-catch in menu methods
- Global exception handler in main loop

## Data Flow Diagram

```
User Input (Scanner)
     ↓
Main.java (UI Layer)
     ↓
Service Layer (Business Logic & Validation)
     ↓
Repository Layer (CRUD Operations)
     ↓
In-Memory Storage (ArrayLists)
     ↓
Entity Objects returned through each layer
```

## Enrollment Flow (Key Use Case)

```
1. User selects "Enroll Student in Course"
2. UI displays active students and courses
3. User enters studentId and courseId
4. Main.enrollStudentInCourse() called
5. StudentService & CourseService verify entities exist
6. EnrollmentService creates Enrollment with ACTIVE status
7. Enrollment added to repository
8. Success logged and displayed to user
```

## Status Update Flow

```
1. User selects "Update Enrollment Status"
2. System displays student's enrollments
3. User enters enrollmentId and new status
4. EnrollmentService updates status
5. If course deactivated:
   - All enrollments marked CANCELLED
   - Student cascade-deactivation triggers
   - Enrollments re-marked CANCELLED
```

## Design Patterns Used

| Pattern | Component | Purpose |
|---------|-----------|---------|
| **Singleton** | FactoryService | Single service instance access |
| **Repository** | *Repository classes | Abstract data access |
| **Service Façade** | *ServiceImpl | Hide complexity, validate, coordinate |
| **Factory** | FactoryService | Create service instances |
| **Data Transfer Object** | Entity classes | Transfer data between layers |
| **Exception Translation** | Service → Repository | Map low-level to domain exceptions |

## Key Design Decisions

### 1. **Defensive Copies in Repositories**
- **Why:** Prevent external code from modifying internal state
- **How:** Return `new ArrayList<>(internalList)` in getters
- **Impact:** Slightly higher memory usage but safer code

### 2. **getId Returns Entity Regardless of Status**
- **Why:** UI needs to display and modify inactive entities
- **How:** Filter on list getters, not on getById
- **Impact:** Clear contracts - "get by ID" vs "get list"

### 3. **Enrollment Lookup by StudentId**
- **Why:** Prevent object identity issues
- **How:** Compare enrollment.getStudent().getStudentId()
- **Impact:** More robust lookups, correct filtering

### 4. **Service Methods Throw Exceptions**
- **Why:** Clearer error semantics (not found ≠ false)
- **How:** EntityNotFoundException for missing entities
- **Impact:** Enables precise error handling in UI

### 5. **Synchronized ID Generation**
- **Why:** Thread-safety for concurrent access
- **How:** Private synchronized increment methods
- **Impact:** Safe but simplified (no UUID, no DB sequences)

## Extension Points for Future Enhancement

### Database Integration
- Replace ArrayLists with JPA entities
- Repositories become Hibernate DAOs
- Services remain largely unchanged

### Caching
- Add Redis or in-memory cache layer
- Cache frequently accessed courses
- Invalidate on updates

### REST API
- Add Spring Boot web layer
- Services become REST endpoints
- Same business logic reused

### Search & Filtering
- Add query builder to repositories
- Support complex searches (name contains, date range)
- Maintain in-memory filtering for now

### Audit Trail
- Add timestamp/user tracking to entities
- Log all modifications
- Generate audit reports

## Performance Considerations

### Current (In-Memory)
- O(n) lookups for getById (linear scan)
- O(n) filtering for status-based queries
- Acceptable for <1000 records

### Future (Database)
- O(1) lookups with proper indexing
- SQL query optimization  
- Prepared statements for security

### Current Bottlenecks
1. ArrayList scanning for unique checks
2. Cascading status updates across enrollments
3. No pagination for large result sets

## Security Considerations

### Current Implementation
- Input validation on email format
- No SQL injection (no database)
- No authentication/authorization
- Single-user console application

### Future Enhancements
- User authentication
- Role-based access control
- Input sanitization all fields
- HTTPS for REST endpoints
- SQL parameterized queries

## Testing Strategy

### Unit Tests (Implemented)
- Repository tests: CRUD operations, search
- Service tests: Business logic, validation
- Util tests: ID generation, email validation
- Coverage: Core logic paths

### Integration Tests (Recommended)
- End-to-end workflow tests
- Multi-operation scenarios (enroll → update → deactivate)
- Data consistency checks
- UI menu flow validation
- Focused helper tests for menu input parsing and user-facing error formatting

### Future
- Performance/load testing
- Security penetration testing
- User acceptance testing

## Logging Architecture (Prepared)

### Current State
- SLF4J with Simple binding
- Logger instances per class
- Logs output to console at INFO level

### Enhancement Ready
- MDC (Mapped Diagnostic Context) hooks prepared
- Session ID tracking in Main
- All critical operations logged with context
- Ready for centralized log aggregation

### Current Implementation
- MDC is active in Main with `sessionId` and `menu` context values
- User-facing validation errors are routed through a single formatter helper

## Deployment Considerations

### Packaging
- Maven assembly plugin for standalone JAR
- Fat JAR with all dependencies included
- Executable JAR with manifest main class

### Distribution
- Single JAR file distribution
- JDK 17 required pre-installed
- No additional dependencies needed

### Scaling
- Current: Single-user console to ~1000 records
- Multi-user: Needs client-server architecture
- High-volume: Requires database + caching
