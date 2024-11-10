import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentTableUI {

    // Method to display all students in a table format
    public static void displayStudents(List<Student> students) {
        // Create JFrame for the table window
        JFrame frame = new JFrame("Display All Students");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        // Column names for the table
        String[] columnNames = {"ID", "Name", "Grade"};

        // Table model to hold the data
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // JTable to display students' data
        JTable table = new JTable(tableModel);

        // Add each student as a row in the table model
        for (Student student : students) {
            Object[] rowData = {student.getId(), student.getName(), student.getGrade()};
            tableModel.addRow(rowData);
        }

        // Add a scroll pane to the table in case there are many students
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make the window visible
        frame.setVisible(true);
    }
}
