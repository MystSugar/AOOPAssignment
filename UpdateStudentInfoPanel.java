package assignment;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

/**
 * Panel for updating student information.
 */
public class UpdateStudentInfoPanel extends JPanel {

    // UI components for student fields
    private JTextField idField, nameField, dobField;
    private JSpinner ageSpinner;
    private JComboBox<String> classCombo;
    private JRadioButton maleButton, femaleButton, otherButton;
    private ButtonGroup genderGroup;
    private JButton updateButton, backButton;
    private Student currentStudent;

    /**
     * Constructs the panel and initializes UI with the given student data.
     */
    public UpdateStudentInfoPanel(Student student) {
        this.currentStudent = student;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Student ID (not editable)
        JLabel idLabel = new JLabel("Student ID:");
        idField = new JTextField(15);
        idField.setText(student.getId());
        idField.setEditable(false); // ID should not be changed
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        // Student Name
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        nameField.setText(student.getName());
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Age (Spinner for numeric input)
        JLabel ageLabel = new JLabel("Age:");
        ageSpinner = new JSpinner(new SpinnerNumberModel(student.getAge(), 16, 80, 1));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(ageLabel, gbc);
        gbc.gridx = 1;
        add(ageSpinner, gbc);

        // Class selection (Dropdown)
        JLabel classLabel = new JLabel("Class:");
        String[] classes = {"Science 1", "Science 2", "Science 3", "Philosophy 1", "Philosophy 2", "Philosophy 3", "Mathematics 1", "Mathematics 2", "Mathematics 3"};
        classCombo = new JComboBox<>(classes);
        classCombo.setSelectedItem(student.getStudentClass());
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(classLabel, gbc);
        gbc.gridx = 1;
        add(classCombo, gbc);

        // Gender selection (Radio buttons)
        JLabel genderLabel = new JLabel("Gender:");
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        otherButton = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        // Panel for gender radio buttons
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(otherButton);

        // Set selected gender based on student data
        String gender = student.getGender();
        if ("Male".equalsIgnoreCase(gender)) {
            maleButton.setSelected(true);
        } else if ("Female".equalsIgnoreCase(gender)) {
            femaleButton.setSelected(true);
        } else {
            otherButton.setSelected(true);
        }

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(genderLabel, gbc);
        gbc.gridx = 1;
        add(genderPanel, gbc);

        // Date of Birth field
        JLabel dobLabel = new JLabel("Date of Birth (DD/MM/YYYY):");
        dobField = new JTextField(15);
        dobField.setText(student.getDob());
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(dobLabel, gbc);
        gbc.gridx = 1;
        add(dobField, gbc);

        // Panel for Update and Back buttons
        updateButton = new JButton("Update Info");
        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Action listeners for buttons
        updateButton.addActionListener(e -> updateStudent());
        backButton.addActionListener(e -> goBack());
    }

    /**
     * Validates input and updates the student information.
     */
    private void updateStudent() {
        String name = nameField.getText().trim();
        int age = (int) ageSpinner.getValue();
        String studentClass = (String) classCombo.getSelectedItem();
        String dob = dobField.getText().trim();
        String gender = maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : otherButton.isSelected() ? "Other" : "";

        // Basic validation for empty fields
        if (name.isEmpty() || gender.isEmpty() || dob.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validate DOB format
        if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Date of Birth must be in DD/MM/YYYY format.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validate DOB value
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dob);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validate age range
        if (age < 16 || age > 80) {
            JOptionPane.showMessageDialog(this, "Age must be between 16 and 80.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update student object with new values
        currentStudent.setName(name);
        currentStudent.setAge(age);
        currentStudent.setStudentClass(studentClass);
        currentStudent.setDob(dob);
        currentStudent.setGender(gender);

        // Persist changes to database
        StudentDatabase.updateStudent(currentStudent);

        JOptionPane.showMessageDialog(this, "Student info updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
