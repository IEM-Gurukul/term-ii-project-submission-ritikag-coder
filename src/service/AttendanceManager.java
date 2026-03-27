package service;

import java.util.ArrayList;
import java.util.List;
import model.Student;

public class AttendanceManager {
    private List<Student> students;

    public AttendanceManager() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Student> getAllStudents() {
        return this.students;
    }

    public void markAttendance(String studentId, boolean isPresent) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                if (isPresent) {
                    student.markPresent();
                } else {
                    student.markAbsent();
                }
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public Student searchById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }
        return result;
    }

    public void displayLowAttendanceWarning() {
        System.out.println("--- Low Attendance Warning (< 75%) ---");
        for (Student student : students) {
            double attendance = student.calculateAttendance();
            if (attendance < 75.0) {
                System.out.println("Warning: Student " + student.getName() + 
                                   " (ID: " + student.getId() + ") " +
                                   "has low attendance: " + String.format("%.2f", attendance) + "%");
            }
        }
    }
}
