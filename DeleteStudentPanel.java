package assignment;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * A JPanel for deleting a student by ID. Provides a text field for entering the
 * student ID and a button to trigger deletion.
 */
public class DeleteStudentPanel extends JPanel {

    private JTextField idField;      // Field to enter student ID
    private JButton deleteButton;    // Button to trigger deletion

    /**
     * Constructs the DeleteStudentPanel UI.
     */
    public DeleteStudentPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(230, 240, 255));
        setBorder(new EmptyBorder(30, 40, 30, 40)); // Padding around the panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Student ID Label ---
        JLabel idLabel = new JLabel("Enter Student ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idLabel.setForeground(new Color(40, 70, 130));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(idLabel, gbc);

        // --- Student ID Text Field ---
        idField = new JTextField(15);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idField.setBackground(new Color(255, 255, 255));
        gbc.gridx = 1;
        add(idField, gbc);

        // --- Delete Button ---
        deleteButton = new JButton("Delete Student");

        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setToolTipText("Click to delete the student from the database");

        // Panel to center the button horizontally
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.add(deleteButton);

        gbc.gridx = 0;
        gbc.gridy = 6; // Place button lower in the panel
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Add action listener to handle deletion when button is clicked
        deleteButton.addActionListener(e -> deleteStudent());
    }

    /**
     * Handles the deletion of a student based on the entered ID. Shows
     * appropriate dialogs for success, not found, or missing input.
     */
    private void deleteStudent() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            // Show error if ID field is empty
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Attempt to find the student by ID
        Student student = StudentDatabase.getStudentById(id);
        if (student != null) {
            // Student found, proceed to delete
            StudentDatabase.deleteStudent(id);
            JOptionPane.showMessageDialog(this, "Student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            idField.setText(""); // Clear the input field
        } else {
            // Student not found
            JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
