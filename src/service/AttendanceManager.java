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
}
