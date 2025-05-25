package AOOPAssignment;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * A JPanel for deleting a student by their ID.
 * Provides a text field for entering the student ID and a button to trigger deletion.
 */
public class DeleteStudentPanel extends JPanel {

    private JTextField idField;     
    private JButton deleteButton;   

    /**
     * Constructs the DeleteStudentPanel UI and sets up event handling.
     */
    public DeleteStudentPanel() {
        setLayout(new GridBagLayout()); 
        setBackground(new Color(230, 240, 255)); 
        setBorder(new EmptyBorder(30, 40, 30, 40)); 

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14); 
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Student ID Label
        JLabel idLabel = new JLabel("Enter Student ID:");
        idLabel.setFont(labelFont);
        idLabel.setForeground(new Color(40, 70, 130)); 

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(idLabel, gbc); 

        // Student ID Text Field
        idField = new JTextField(15);
        idField.setFont(fieldFont);
        idField.setToolTipText("Enter the unique ID of the student to delete");
        idField.setBackground(new Color(255, 255, 255));

        gbc.gridx = 1;
        add(idField, gbc); 

        // Delete Button
        deleteButton = new JButton("Delete Student");
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        deleteButton.setToolTipText("Click to delete the student from the database");

        // Panel to center the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.add(deleteButton);

        gbc.gridx = 0;
        gbc.gridy = 6; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc); 

        // Add action listener to handle delete button click
        deleteButton.addActionListener(e -> deleteStudent());
    }

    /**
     * Handles the deletion of a student when the delete button is pressed.
     * Validates input, checks if the student exists, and deletes if found.
     */
    private void deleteStudent() {
        String id = idField.getText().trim(); // Get entered student ID

        // Check if ID field is empty
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Attempt to find the student by ID
        Student student = StudentDatabase.getStudentById(id);
        if (student != null) {
            // Student found, delete and show success message
            StudentDatabase.deleteStudent(id);
            JOptionPane.showMessageDialog(this, "Student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            idField.setText(""); // Clear the input field
        } else {
            // Student not found, show error message
            JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
