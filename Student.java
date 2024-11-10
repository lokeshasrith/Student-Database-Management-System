import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getGrade() { return grade; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setGrade(double grade) { this.grade = grade; }

    // Implement compareTo to sort by ID (if needed)
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Grade: " + grade;
    }
}
