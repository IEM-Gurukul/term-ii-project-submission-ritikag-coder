package model;

public class Student extends Person {
    private int totalClasses;
    private int presentCount;

    public Student(String id, String name) {
        super(id, name);
        this.totalClasses = 0;
        this.presentCount = 0;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void markPresent() {
        this.totalClasses++;
        this.presentCount++;
    }

    public void markAbsent() {
        this.totalClasses++;
    }
}
