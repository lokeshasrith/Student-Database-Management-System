import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDatabase database = new StudentDatabase(); // Initialize the student database

        // Main menu for user interaction
        while (true) {
            System.out.println("\nStudent Database Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Display All Students");
            System.out.println("6. Sort Students by Name");
            System.out.println("7. Sort Students by Grade");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    // Add student
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Grade: ");
                    double grade = scanner.nextDouble();
                    database.addStudent(new Student(id, name, grade)); // Add student
                    System.out.println("Student added successfully.");
                }

                case 2 -> {
                    // Remove student
                    System.out.print("Enter Student ID to remove: ");
                    int removeId = scanner.nextInt();
                    if (database.removeStudent(removeId)) {
                        System.out.println("Student removed successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                }

                case 3 -> {
                    // Update student
                    System.out.print("Enter Student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new grade: ");
                    double newGrade = scanner.nextDouble();
                    if (database.updateStudent(updateId, newName, newGrade)) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                }

                case 4 -> {
                    // Search student by name
                    System.out.print("Enter Student Name to search: ");
                    scanner.nextLine(); // Consume newline
                    String searchName = scanner.nextLine();
                    var foundStudents = database.searchByName(searchName);
                    if (foundStudents.isEmpty()) {
                        System.out.println("No student found with that name.");
                    } else {
                        foundStudents.forEach(System.out::println);
                    }
                }

                case 5 -> // Display all students in a table format
                    StudentTableUI.displayStudents(database.getStudents());

                case 6 -> {
                    // Sort students by name
                    database.sortByName();
                    System.out.println("Students sorted by name.");
                }

                case 7 -> {
                    // Sort students by grade
                    database.sortByGrade();
                    System.out.println("Students sorted by grade (descending).");
                }

                case 8 -> {
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
