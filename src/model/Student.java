package model;

public class Student extends Person {
    private int totalClasses;
    private int presentCount;
    private int lateCount;

    public Student(String id, String name) {
        super(id, name);
        this.totalClasses = 0;
        this.presentCount = 0;
        this.lateCount = 0;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getLateCount() {
        return lateCount;
    }

    public void setLateCount(int lateCount) {
        this.lateCount = lateCount;
    }

    public void markPresent() {
        this.totalClasses++;
        this.presentCount++;
    }

    public void markAbsent() {
        this.totalClasses++;
    }

    public void markLate() {
        this.totalClasses++;
        this.presentCount++; // counted as present but recorded as late
        this.lateCount++;
    }

    public double calculateAttendance() {
        if (totalClasses == 0) {
            return 0.0;
        }
        return ((double) presentCount / totalClasses) * 100.0;
    }
}
