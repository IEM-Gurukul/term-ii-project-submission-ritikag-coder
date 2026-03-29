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

    public void markAttendance(String studentId, String date, String status) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                boolean valid = false;
                if (status.equalsIgnoreCase("p") || status.equalsIgnoreCase("present")) {
                    student.markPresent();
                    valid = true;
                } else if (status.equalsIgnoreCase("a") || status.equalsIgnoreCase("absent")) {
                    student.markAbsent();
                    valid = true;
                } else if (status.equalsIgnoreCase("l") || status.equalsIgnoreCase("late")) {
                    student.markLate();
                    valid = true;
                } else {
                    System.out.println("Invalid status provided. Please use P, A, or L.");
                    return;
                }
                
                if (valid) {
                    try (java.io.FileWriter fw = new java.io.FileWriter("attendance_log.txt", true);
                         java.io.BufferedWriter bw = new java.io.BufferedWriter(fw)) {
                        bw.write(date + " | ID: " + studentId + " | Name: " + student.getName() + " | Status: " + status.toUpperCase());
                        bw.newLine();
                    } catch (java.io.IOException e) {
                        System.out.println("Warning: Failed to write to attendance_log.txt");
                    }
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

        System.out.println("      CRITICAL: LOW ATTENDANCE ALERTS     ");

        boolean hasWarnings = false;
        for (Student student : students) {
            double attendance = student.calculateAttendance();
            if (student.getTotalClasses() > 0 && attendance < 75.0) {
                hasWarnings = true;
                int requiredClasses = (int) Math
                        .ceil((0.75 * student.getTotalClasses() - student.getPresentCount()) / 0.25);
                System.out.printf("[!] %s (ID: %s) currently at %.2f%%\n", student.getName(), student.getId(),
                        attendance);
                if (requiredClasses > 0) {
                    System.out.printf(
                            "    Action mapping -> Needs to attend %d more consecutive class(es) to reach 75%%.\n",
                            requiredClasses);
                }
            }
        }
        if (!hasWarnings) {
            System.out.println("[+] All active students are currently above 75% attendance.");
        }
        System.out.println("==========================================\n");
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
