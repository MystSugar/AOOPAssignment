package assignment;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * MainMenu class represents the main window of the Student Information System.
 * It uses a JFrame with a tabbed pane for different student operations.
 */
public class MainMenu extends JFrame {

    /**
     * Constructor initializes the main menu window and its components.
     */
    public MainMenu() {
        // Set the window title
        setTitle("Student Information System");
        // Set the window size
        setSize(600, 450);
        // Exit the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window on the screen
        setLocationRelativeTo(null);

        // Set a light background color for the main content pane
        getContentPane().setBackground(new Color(245, 248, 255));

        // Create a title label for the window
        JLabel titleLabel = new JLabel("Student Information System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Set font style and size
        titleLabel.setForeground(new Color(40, 70, 140)); // Set text color
        titleLabel.setBorder(new EmptyBorder(8, 0, 4, 0)); // Add padding above and below

        // Create a tabbed pane to hold different panels for student operations
        JTabbedPane tabbedPane = new JTabbedPane();
        // Add a tab for viewing students
        tabbedPane.addTab("Student Panel", new ViewStudentsPanel());
        // Add a tab for adding a new student
        tabbedPane.addTab("Add", new AddStudentPanel());
        // Add a tab for updating student information
        tabbedPane.addTab("Update", new UpdateStudentPanel());
        // Add a tab for deleting a student
        tabbedPane.addTab("Delete", new DeleteStudentPanel());
        // Add a tab for searching students
        tabbedPane.addTab("Search", new SearchStudentPanel());

        // Style the tabbed pane's background and foreground colors
        tabbedPane.setBackground(new Color(255, 255, 255));
        tabbedPane.setForeground(new Color(40, 70, 140));
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 13)); // Set tab font
        tabbedPane.setBorder(new EmptyBorder(8, 12, 8, 12)); // Add padding around the tabs

        // Use BorderLayout for the main frame to arrange components
        setLayout(new BorderLayout());
        // Add the title label to the top (NORTH) of the frame
        add(titleLabel, BorderLayout.NORTH);
        // Add the tabbed pane to the center of the frame
        add(tabbedPane, BorderLayout.CENTER);

        // Make the window visible
        setVisible(true);
    }

    /**
     * Main method to launch the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Ensure GUI creation runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
