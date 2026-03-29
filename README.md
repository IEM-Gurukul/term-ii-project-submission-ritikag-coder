<img width="178" height="56" alt="image" src="https://github.com/user-attachments/assets/45e05f14-b150-49da-8169-654a37ece494" />[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# PCCCS495 – Term II Project

## Project Title
Student Attendance Management System 

---

## Problem Statement (max 150 words)
Tracking attendance manually in schools is inefficient, error-prone, and lacks realtime access.
Teachers depend on paper registers or spreadsheets, which do not automatically calculate attendance or create
reports.The proposed Student Attendance Management System aims to provide an organized, object-oriented
solution for managing student attendance records efficiently. The console based system allows instructors to add and manage
student information, record attendance for each session, automatically calculate attendance percentages, and
generate attendance reports. By incorporating persistent storage and modular design principles, the system ensures
reliability, improved usability, and easier maintenance while demonstrating key object-oriented programming
concepts.

---

## Target User
 Educational institutions such as schools, colleges, and training centers where instructors need a
structured and reliable solution to track, analyze, and manage student attendance records efficiently

---

## Core Features
• Student Record Management: Add and manage student profiles with unique identifiers.

• Attendance Logging: Mark attendance (Present / Absent) for students on specific dates or sessions.

• Attendance Percentage Calculation: Automatically calculate attendance percentages for each student.

• Search Student Feature: Quickly search for students by ID or name.

• Low Attendance Warning: Display alerts when a student's attendance falls below a defined threshold (e.g., 75%).

• Attendance Reports: Generate summaries showing attendance statistics for individual students or the entire class.

• Data Persistence: Store and retrieve attendance records to maintain data between system sessions.

• Input Validation: Handle invalid inputs and ensure data consistency during operations

---

## OOP Concepts Used

• Abstraction: Separate layers for model, service, data access, and user interaction.

• Encapsulation: Student and AttendanceRecord classes maintain private attributes accessed through public getters
and setters.

• Inheritance: A base Person class defines shared attributes such as ID and name, while Student extends it with
attendance-related properties.

• Polymorphism: Methods such as displayInfo() can be overridden to present attendance data in different formats.

• Exception Handling: Manage invalid student IDs, missing records, and input errors gracefully.

• Collections: Dynamic structures such as ArrayList manage student objects efficiently. 

---

## Proposed Architecture Description
The system follows a layered object-oriented architecture where a base Person class defines common attributes such
as id and name, and the Student class extends it to include attendance-related data such as present count and total
classes. A central AttendanceManager coordinates core operations such as adding students, marking attendance,
calculating percentages, and generating reports. A separate data persistence layer manages storage and retrieval of
student and attendance records, ensuring clear separation between application logic and data management.Person
class → Student class → Attendance Manager → Persistence layer 

---

## How to Run
Run the program:
In terminal

The menu-driven system will start.

Follow on-screen options:
1)Add Student

2)Mark Attendance

3)View Reports

4)Exit

Data will be automatically saved and loaded from file.


---

## Git Discipline Notes
Minimum 10 meaningful commits required.
