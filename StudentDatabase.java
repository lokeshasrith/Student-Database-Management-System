import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentDatabase {
    private List<Student> students;
    private final String filePath = "students.dat"; // File to save student data

    public StudentDatabase() {
        students = loadFromFile(); // Load students from file when the program starts
    }

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    // Remove a student by ID
    public boolean removeStudent(int id) {
        boolean removed = students.removeIf(student -> student.getId() == id);
        if (removed) saveToFile(); // Save after removal
        return removed;
    }

    // Update student details (name and grade)
    public boolean updateStudent(int id, String newName, double newGrade) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(newName);
                student.setGrade(newGrade);
                saveToFile(); // Save after update
                return true;
            }
        }
        return false;
    }

    // Search students by name (case-insensitive)
    public List<Student> searchByName(String name) {
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    // Sort students by name
    public void sortByName() {
        students.sort(Comparator.comparing(Student::getName));
    }

    // Sort students by grade (descending)
    public void sortByGrade() {
        students.sort(Comparator.comparingDouble(Student::getGrade).reversed());
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println); // Print each student
        }
    }

    // Save student data to file
    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(students); // Serialize the students list to file
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load students from file
    @SuppressWarnings("unchecked")
    private List<Student> loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Student>) in.readObject(); // Deserialize students list
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Return empty list if file not found
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get all students (for GUI display)
    public List<Student> getStudents() {
        return new ArrayList<>(students); // Return a copy to avoid external modification
    }
}
