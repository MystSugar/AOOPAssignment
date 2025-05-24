package assignment;

import java.awt.*;
import java.util.Map;
import javax.swing.*;

/**
 * Panel for updating a student's grades for different subjects.
 */
public class UpdateStudentGradesPanel extends JPanel {

    private Student currentStudent;
    private JComboBox<String> subjectCombo;
    private JTextField midtermField, finalsField;
    private JLabel letterGradeLabel;
    private JButton updateButton, backButton;
    private JTextArea gradesArea;

    // List of subjects available for grading
    private static final String[] SUBJECTS = {"Maths", "Science", "English"};

    /**
     * Constructs the panel for updating grades for a given student.
     *
     * @param student The student whose grades are to be updated.
     */
    public UpdateStudentGradesPanel(Student student) {
        this.currentStudent = student;
        setLayout(new BorderLayout());

        // --- Input Panel for grade entry ---
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Subject selection dropdown
        JLabel subjectLabel = new JLabel("Subject:");
        subjectCombo = new JComboBox<>(SUBJECTS);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(subjectLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(subjectCombo, gbc);

        // Midterm grade input
        JLabel midtermLabel = new JLabel("Midterm (out of 100):");
        midtermField = new JTextField(5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(midtermLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(midtermField, gbc);

        // Finals grade input
        JLabel finalsLabel = new JLabel("Finals (out of 100):");
        finalsField = new JTextField(5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(finalsLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(finalsField, gbc);

        // Letter grade display label
        letterGradeLabel = new JLabel("Letter Grade: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(letterGradeLabel, gbc);

        // --- Buttons for updating and going back ---
        updateButton = new JButton("Update Grade");
        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // --- Area to display all grades for the student ---
        gradesArea = new JTextArea(8, 40);
        gradesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gradesArea);
        add(scrollPane, BorderLayout.CENTER);

        // Show current grades in the text area
        displayGrades();

        // --- Event listeners for UI actions ---
        updateButton.addActionListener(e -> updateGrade());
        backButton.addActionListener(e -> goBack());
        subjectCombo.addActionListener(e -> fillGradeFields());
        fillGradeFields(); // Fill fields for the first subject by default
    }

    /**
     * Fills the midterm and finals fields with the current grades for the
     * selected subject.
     */
    private void fillGradeFields() {
        String subject = (String) subjectCombo.getSelectedItem();
        if (subject == null) {
            return;
        }
        Grade grade = currentStudent.getAllGrades().get(subject);
        if (grade != null) {
            midtermField.setText(String.valueOf(grade.getMidterm()));
            finalsField.setText(String.valueOf(grade.getFinals()));
            letterGradeLabel.setText("Letter Grade: " + grade.getLetterGrade());
        } else {
            midtermField.setText("");
            finalsField.setText("");
            letterGradeLabel.setText("Letter Grade: ");
        }
    }

    /**
     * Updates the grade for the selected subject after validating input.
     */
    private void updateGrade() {
        String subject = (String) subjectCombo.getSelectedItem();
        String midStr = midtermField.getText().trim();
        String finalsStr = finalsField.getText().trim();

        // Validate that fields are not empty
        if (midStr.isEmpty() || finalsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int midterm, finals;
        try {
            midterm = Integer.parseInt(midStr);
            finals = Integer.parseInt(finalsStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Grades must be integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate grade ranges
        if (midterm < 0 || midterm > 100 || finals < 0 || finals > 100) {
            JOptionPane.showMessageDialog(this, "Grades must be between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update grade in student object and database
        Grade grade = new Grade(midterm, finals);
        currentStudent.setGrade(subject, grade);
        StudentDatabase.updateStudent(currentStudent);

        // Update UI to reflect new grade
        letterGradeLabel.setText("Letter Grade: " + grade.getLetterGrade());
        JOptionPane.showMessageDialog(this, "Grade updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        displayGrades();
    }

    /**
     * Displays all grades for the student in the text area.
     */
    private void displayGrades() {
        gradesArea.setText("");
        Map<String, Grade> grades = currentStudent.getAllGrades();
        for (String subject : SUBJECTS) {
            Grade grade = grades.get(subject);
            if (grade != null) {
                gradesArea.append(subject + ": " + grade + "\n");
            } else {
                gradesArea.append(subject + ": No grade\n");
            }
        }
    }

    /**
     * Returns to the main menu panel.
     */
    private void goBack() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(new MainMenu());
        frame.revalidate();
    }
}
