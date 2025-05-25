package AOOPAssignment;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Main Window for the Student Information System
public class MainMenu extends JFrame {

    // Constructor to set up the main menu window
    public MainMenu() {
        setTitle("Student Information System"); 
        setSize(600, 450); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); // Center the window on the screen
        getContentPane().setBackground(new Color(245, 248, 255)); 

        // Create and style the title label
        JLabel titleLabel = new JLabel("Student Information System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); 
        titleLabel.setForeground(new Color(40, 70, 140)); 
        titleLabel.setBorder(new EmptyBorder(8, 0, 4, 0)); 

        // Create a tabbed pane for different student operations
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Student Panel", new ViewStudentOpsPanel()); 
        tabbedPane.addTab("Add", new AddStudentPanel()); 
        tabbedPane.addTab("Update", new UpdateStudentPanel()); 
        tabbedPane.addTab("Delete", new DeleteStudentPanel()); 
        tabbedPane.addTab("Search", new SearchStudentPanel()); 

        // Style the tabbed pane
        tabbedPane.setBackground(new Color(255, 255, 255)); 
        tabbedPane.setForeground(new Color(40, 70, 140)); 
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 13)); 
        tabbedPane.setBorder(new EmptyBorder(8, 12, 8, 12)); 

        // Set layout and add components to the frame
        setLayout(new BorderLayout()); 
        add(titleLabel, BorderLayout.NORTH); 
        add(tabbedPane, BorderLayout.CENTER); 

        setVisible(true); 
    }

    // Main method to launch the application
    public static void main(String[] args) {
        // Ensure GUI is created on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
