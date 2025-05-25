package AOOPAssignment;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel for updating and displaying a student's grades for different subjects.
 */
public class UpdateStudentGradesPanel extends JPanel {

    private Student currentStudent; // The student whose grades are being updated
    private JComboBox<String> subjectCombo; // Dropdown for subject selection
    private JTextField midtermField, finalsField; // Input fields for grades
    private JLabel letterGradeLabel; // Displays the letter grade
    private JButton updateButton, backButton; // Buttons for actions
    private JTextArea gradesArea; // Area to display all grades

    // List of subjects available for grading
    private static final String[] SUBJECTS = {"Maths", "Science", "English"};

    /**
     * Constructs the panel for updating student grades.
     * @param student The student whose grades are to be managed.
     */
    public UpdateStudentGradesPanel(Student student) {
        this.currentStudent = student;
        setLayout(new BorderLayout());
        setBackground(new Color(230, 240, 255));
        setBorder(new EmptyBorder(30, 40, 30, 40));

        // Input Panel for grade entry
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        inputPanel.setBackground(new Color(230, 240, 255));
        gbc.insets = new Insets(8, 8, 8, 8); 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Subject selection dropdown
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(labelFont);
        subjectCombo = new JComboBox<>(SUBJECTS);
        subjectCombo.setFont(fieldFont);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(subjectLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(subjectCombo, gbc);

        // Midterm grade input
        JLabel midtermLabel = new JLabel("Midterm (out of 100):");
        midtermLabel.setFont(labelFont);
        midtermField = new JTextField(5);
        midtermField.setFont(fieldFont);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(midtermLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(midtermField, gbc);

        // Finals grade input
        JLabel finalsLabel = new JLabel("Finals (out of 100):");
        finalsLabel.setFont(labelFont);
        finalsField = new JTextField(5);
        finalsField.setFont(fieldFont);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(finalsLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(finalsField, gbc);

        // Letter grade display label
        letterGradeLabel = new JLabel("Letter Grade: ");
        letterGradeLabel.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(letterGradeLabel, gbc);

        // Buttons for updating and going back 
        updateButton = new JButton("Update Grade");
        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 240, 255));

        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Area to display all grades for the student
        gradesArea = new JTextArea(8, 40);
        gradesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gradesArea);
        add(scrollPane, BorderLayout.CENTER);

        displayGrades(); // Show current grades

        // Event listeners for buttons and subject selection
        updateButton.addActionListener(e -> updateGrade());
        backButton.addActionListener(e -> goBack());
        subjectCombo.addActionListener(e -> fillGradeFields());
        fillGradeFields(); // Initialize fields for the first subject
    }

    /**
     * Fills the grade fields with the current grades for the selected subject.
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

        // Validate input fields
        if (midStr.isEmpty() || finalsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int midterm, finals;
        try {
            midterm = Integer.parseInt(midStr);
            finals = Integer.parseInt(finalsStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Grades must be numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check grade range
        if (midterm < 0 || midterm > 100 || finals < 0 || finals > 100) {
            JOptionPane.showMessageDialog(this, "Grades must be between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update grade and database
        Grade grade = new Grade(midterm, finals);
        currentStudent.setGrade(subject, grade);
        StudentDatabase.updateStudent(currentStudent);

        letterGradeLabel.setText("Letter Grade: " + grade.getLetterGrade());
        JOptionPane.showMessageDialog(this, "Grade updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        displayGrades(); // Refresh displayed grades
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
                gradesArea.append(" " + subject + ": " + grade + "\n");
            } else {
                gradesArea.append(" " + subject + ": No grade\n");
            }
        }
    }

    /**
     * Navigates back to the main menu.
     */
    private void goBack() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(new MainMenu());
        frame.revalidate();
    }
}
