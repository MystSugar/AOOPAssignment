package AOOPAssignment;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel for updating student information or grades.
 */
public class UpdateStudentPanel extends JPanel {

    private JTextField idField;      // Field to input student ID
    private JButton loadButton;      // Button to trigger loading student
    private Student currentStudent;  // Holds the currently loaded student

    /**
     * Constructs the UpdateStudentPanel UI.
     */
    public UpdateStudentPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(230, 240, 255));
        setBorder(new EmptyBorder(30, 40, 30, 40)); // Padding around the panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // --- Student ID Label ---
        JLabel idLabel = new JLabel("Enter Student ID:");
        idLabel.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(idLabel, gbc);

        // --- Student ID Text Field ---
        idField = new JTextField(15);
        idField.setFont(fieldFont);
        idField.setBackground(new Color(255, 255, 255));
        idField.setToolTipText("Enter the student ID to update");

        gbc.gridx = 1;
        add(idField, gbc);

        // --- Load Button ---
        loadButton = new JButton("Load");
        loadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loadButton.setToolTipText("Click to load the student from the database");

        // Panel to center the button horizontally
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.add(loadButton);

        gbc.gridx = 0;
        gbc.gridy = 6; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Add action listener to handle loading student when button is clicked
        loadButton.addActionListener(e -> loadStudent());
    }

    /**
     * Loads the student with the entered ID and prompts for update type.
     */
    private void loadStudent() {
        String id = idField.getText().trim();
        currentStudent = StudentDatabase.getStudentById(id); // Fetch student from database
        if (currentStudent == null) {
            // Show error if student not found
            JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ask user what they want to update
        Object[] options = {"Update Info", "Update Grades"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "What do you want to update for this student?",
                "Choose Update Type",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Show appropriate panel based on user choice
        if (choice == JOptionPane.YES_OPTION) {
            showInfoPanel();
        } else if (choice == JOptionPane.NO_OPTION) {
            showGradesPanel();
        }
    }

    /**
     * Switches to the panel for updating student information.
     */
    private void showInfoPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(new UpdateStudentInfoPanel(currentStudent));
        frame.revalidate();
    }

    /**
     * Switches to the panel for updating student grades.
     */
    private void showGradesPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(new UpdateStudentGradesPanel(currentStudent));
        frame.revalidate();
    }
}
