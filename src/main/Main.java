package main;

import java.util.Scanner;
import model.Student;
import service.AttendanceManager;

public class Main {
    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();
        Scanner scanner = new Scanner(System.in);
        String FILENAME = "students_data.csv";

        System.out.println("Trying to load existing data...");
        manager.loadDataFromFile(FILENAME);

        while (true) {
            System.out.println("\n--- Student Attendance Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Reports");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    manager.addStudent(new Student(id, name));
                    System.out.println("Student added successfully.");
                    break;
                case "2":
                    System.out.print("Enter Student ID to mark attendance: ");
                    String attId = scanner.nextLine();
                    Student s = manager.searchById(attId);
                    if (s != null) {
                        System.out.print("Is student present? (y/n): ");
                        String present = scanner.nextLine();
                        manager.markAttendance(attId, present.equalsIgnoreCase("y"));
                        System.out.println("Attendance marked.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case "3":
                    System.out.println("\n--- Detailed Attendance Reports ---");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.printf("%-10s | %-20s | %-13s | %-13s | %-10s\n", 
                                      "ID", "Name", "Total Classes", "Present Count", "Attendance");
                    System.out.println("-------------------------------------------------------------------");
                    for (Student student : manager.getAllStudents()) {
                        System.out.printf("%-10s | %-20s | %-13d | %-13d | %.2f%%\n",
                                student.getId(), student.getName(), 
                                student.getTotalClasses(), student.getPresentCount(), 
                                student.calculateAttendance());
                    }
                    System.out.println("-------------------------------------------------------------------");
                    manager.displayLowAttendanceWarning();
                    break;
                case "4":
                    System.out.println("Saving data...");
                    manager.saveDataToFile(FILENAME);
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
