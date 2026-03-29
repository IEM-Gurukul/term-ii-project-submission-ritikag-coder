package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public void saveDataToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                bw.write(s.getId() + "," + s.getName() + "," + s.getTotalClasses() + "," + s.getPresentCount());
                bw.newLine();
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadDataFromFile(String filename) {
        this.students.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Student s = new Student(parts[0], parts[1]);
                    s.setTotalClasses(Integer.parseInt(parts[2]));
                    s.setPresentCount(Integer.parseInt(parts[3]));
                    this.students.add(s);
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
