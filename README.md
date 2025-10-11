# Student Grade Management System - Documentation

## Overview
A console-based Java application to manage student names and grades. This system allows you to add students, calculate statistics, and search for student records.

---

## Features

### 1. Add Student
- Adds a single student with name and grade
- Validates grade (must be 0-100)
- Maximum 50 students

### 2. Display All Students
- Shows list of all students with their grades
- Displays class average at the end

### 3. Find Highest Grade
- Finds and displays the student with the highest grade

### 4. Find Lowest Grade
- Finds and displays the student with the lowest grade

### 5. Grade Distribution
- Shows how many students got each letter grade
- A (90-100), B (80-89), C (70-79), D (60-69), F (0-59)

### 6. Search Student
- Search for a student by name
- Case-insensitive search
- Displays their grade if found

### 7. Add Multiple Students
- Add several students at once using variable arguments (varargs)

### 8. Exit
- Closes the application

---

## How to Run

1. Open your IDE (IntelliJ, Eclipse, VS Code)
2. Create a new Java project
3. Create a file: `StudentGradeSystem.java`
4. Copy your code into the file
5. Run the `main` method
6. Follow the menu prompts

---

## Data Structure

The system uses **parallel arrays**:
- `String[] studentNames` - stores student names
- `double[] studentGrades` - stores corresponding grades
- `int studentCount` - tracks number of students added

**Example:**
Index:  0        1       2  
Names:  [Alice] [Bob]   [Charlie]  
Grades: [85.5]  [92.0]  [78.5]

---

## Method Reference

### Instance Methods
| Method | Purpose | Returns |
|--------|---------|---------|
| `addStudent(String, double)` | Add a single student | boolean |
| `displayAllStudents()` | Show all students | void |
| `findHighestGrade()` | Find top student | void |
| `findLowestGrade()` | Find lowest scoring student | void |
| `displayGradeDistribution()` | Show grade breakdown | void |
| `searchStudent(String)` | Find student by name | boolean |
| `addMultipleStudents(String...)` | Add multiple students | void |
| `displayMenu()` | Show menu options | void |

### Static Methods
| Method | Purpose | Returns |
|--------|---------|---------|
| `calculateAverage(double[], int)` | Calculate average grade | double |
| `main(String[])` | Program entry point | void |

---

## Usage Examples

### Example 1: Adding a Student
Menu Option: 1  
Enter student name: Alice  
Enter grade: 85.5  
Output: Student added successfully

### Example 2: Searching for a Student
Menu Option: 6  
Enter student name to search: alice  
Output:  
Student: Alice  
Grade: 85.5

### Example 3: Grade Distribution
Menu Option: 5  
Output:  
Grade Distribution:  
A (90-100): 2  
B (80-89): 3  
C (70-79): 1  
D (60-69): 0  
F (0-59): 0

---

## Input Validation

### Grade Validation
- Must be between 0 and 100
- Must be a valid number (double)

### Capacity Check
- Maximum 50 students
- Shows error if limit reached

### Empty Name Check
- Names cannot be empty
- Prompts for valid input

---

## Key Concepts Used

| Concept | Where It's Used |
|---------|----------------|
| **Arrays** | Storing student names and grades |
| **Loops (for)** | Searching, calculating, displaying data |
| **Conditionals (if-else)** | Input validation, grade classification |
| **Switch Statement** | Menu selection |
| **Static Methods** | calculateAverage() - utility function |
| **Instance Methods** | All methods that work with class data |
| **Varargs** | addMultipleStudents() |
| **Access Modifiers** | private arrays, public methods |
| **Variables** | studentCount, grade totals, indexes |
| **Operators** | Arithmetic (average), comparison (finding min/max) |

---

## Common Issues & Solutions

### Issue: "Array Index Out of Bounds"
**Solution:** Check that studentCount doesn't exceed MAX_STUDENTS

### Issue: Grade shows as 0.0 when it shouldn't
**Solution:** Make sure you're reading input as `double`, not `int`

### Issue: Student search not working
**Solution:** Use `.equalsIgnoreCase()` for case-insensitive comparison

### Issue: Average calculation is wrong
**Solution:** Ensure you're dividing by `studentCount`, not `MAX_STUDENTS`

---

## Future Enhancements

- [ ] Edit student grades
- [ ] Delete students
- [ ] Sort students by name or grade
- [ ] Save data to file
- [ ] Load data from file
- [ ] Calculate median and mode
- [ ] Export to CSV

---

## Code Structure
StudentGradeSystem  
│  
├── Instance Variables  
│   ├── MAX_STUDENTS (final int)  
│   ├── studentNames[] (String array)  
│   ├── studentGrades[] (double array)  
│   └── studentCount (int)  
│  
├── Static Methods  
│   ├── calculateAverage()  
│   └── main()  
│  
└── Instance Methods  
├── addStudent()  
├── displayAllStudents()  
├── findHighestGrade()  
├── findLowestGrade()  
├── displayGradeDistribution()  
├── searchStudent()  
├── addMultipleStudents()  
└── displayMenu()

---

## Testing Checklist

- [ ] Add a student successfully
- [ ] Try adding with invalid grade (negative, >100)
- [ ] Add maximum students (50)
- [ ] Try adding 51st student (should fail)
- [ ] Display all students
- [ ] Find highest grade
- [ ] Find lowest grade
- [ ] Search for existing student
- [ ] Search for non-existing student
- [ ] Display grade distribution
- [ ] Add multiple students at once
- [ ] Calculate average correctly

---

## Author Notes

**Learning Goals Achieved:**
- Working with arrays
- Creating and using methods
- Understanding static vs instance
- Input validation
- Menu-driven programs
- Basic data management

**Next Steps:**
- Try adding new features from "Future Enhancements"
- Move on to Project 2: Banking System
- Consider refactoring with Objects (after learning OOP)

---

**Version:** 1.0  
**Date:** October 2025  
**Project Type:** Learning Project - Java Fundamentals
