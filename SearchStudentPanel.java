package assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A JPanel that allows users to search for a student by ID and displays the
 * student's details.
 */
public class SearchStudentPanel extends JPanel {

    private JTextField idField;      // Field for entering student ID
    private JTextArea resultArea;    // Area to display search results
    private JButton searchButton;    // Button to trigger search

    /**
     * Constructs the search panel UI and sets up event handling.
     */
    public SearchStudentPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(230, 240, 255));
        setForeground(new java.awt.Color(0, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Label for student ID input
        JLabel idLabel = new JLabel("Enter Student ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(idLabel, gbc);

        // Text field for entering student ID
        idField = new JTextField(18);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal expansion
        gbc.weightx = 1.0; // Give extra space to the text field
        add(idField, gbc);
        gbc.weightx = 0; // Reset for other components
        gbc.fill = GridBagConstraints.NONE;

        // Search button
        searchButton = new JButton("Search Student");

        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setToolTipText("Click to search for the student in the database");

        // Panel to center the button horizontally
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.add(searchButton);

        gbc.gridx = 0;
        gbc.gridy = 6; // Place button lower in the panel
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Add action listener to handle deletion when button is clicked
        searchButton.addActionListener(e -> searchStudent());

        // Area to display search results, inside a scroll pane
        resultArea = new JTextArea(28, 70); // Increased rows for a bigger area
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Optional: larger, readable font
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Make the scroll pane expand horizontally and vertically
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span across both columns
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Reset gridbag constraints for next components
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;

        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent(); // Perform search when button is clicked
            }
        });
    }

    /**
     * Searches for a student by ID and displays the result in the resultArea.
     */
    private void searchStudent() {
        String id = idField.getText().trim();
        resultArea.setText(""); // Clear previous results

        // Show error if ID field is empty
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve student from database
        Student s = StudentDatabase.getStudentById(id);
        if (s != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ").append(s.getId()).append("\n");
            sb.append("Name: ").append(s.getName()).append("\n");
            sb.append("Gender: ").append(s.getGender()).append("\n");
            sb.append("Age: ").append(s.getAge()).append("\n");
            sb.append("Class: ").append(s.getStudentClass()).append("\n");
            sb.append("DOB: ").append(s.getDob()).append("\n");
            sb.append("\n\n");

            // List of subjects to display grades for
            String[] subjects = {"Maths", "Science", "English"};
            sb.append("Grades:\n");
            sb.append("-----------------------------\n");
            for (String subject : subjects) {
                sb.append(subject).append(":\n");
                Grade grade = s.getGrade(subject);
                if (grade != null) {
                    sb.append("").append(grade.toString()).append("\n");
                } else {
                    sb.append("No grade available\n");
                }
                sb.append("-----------------------------\n");
            }

            resultArea.setText(sb.toString()); // Display student info
        } else {
            resultArea.setText("Student not found."); // Student not found
        }
    }
}
