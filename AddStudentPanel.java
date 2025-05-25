package AOOPAssignment;

import java.awt.*;
import javax.swing.*;

/**
 * AddStudentPanel is a JPanel that provides a form for adding a new student.
 * It collects student information such as ID, name, date of birth, age, class, and gender.
 */
public class AddStudentPanel extends JPanel {

    // Fields for student information input
    private JTextField idField, nameField;
    private JSpinner ageSpinner, dobSpinner;
    private JComboBox<String> classCombo;
    private JRadioButton maleButton, femaleButton, otherButton;
    private ButtonGroup genderGroup;
    private JButton addButton;

    /**
     * Constructor initializes the panel and its components.
     */
    public AddStudentPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(230, 240, 255));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Use GridBagLayout for flexible component arrangement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Define consistent fonts for labels and fields
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Student ID label and input field
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(labelFont);

        idField = new JTextField(15);
        idField.setFont(fieldFont);
        idField.setToolTipText("Enter the unique ID for the student");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        // Name label and input field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);

        nameField = new JTextField(15);
        nameField.setFont(fieldFont);
        nameField.setToolTipText("Enter the full name of the student");

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Date of Birth label and spinner (date picker)
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(labelFont);

        // JSpinner for date selection using SpinnerDateModel
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dobSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "dd/MM/yyyy");
        dobSpinner.setEditor(dateEditor);
        dobSpinner.setFont(fieldFont);
        dobSpinner.setToolTipText("Pick the date of birth (DD/MM/YYYY)");

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(dobLabel, gbc);
        gbc.gridx = 1;
        add(dobSpinner, gbc);

        // Age label and spinner (non-editable, calculated from DOB)
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);

        // Spinner for age, disabled for direct editing
        ageSpinner = new JSpinner(new SpinnerNumberModel(18, 16, 80, 1));
        ((JSpinner.DefaultEditor) ageSpinner.getEditor()).getTextField().setFont(fieldFont);
        ((JSpinner.DefaultEditor) ageSpinner.getEditor()).getTextField().setDisabledTextColor(Color.BLACK);
        ageSpinner.setToolTipText("Age is calculated from Date of Birth and cannot be edited directly.");
        ageSpinner.setEnabled(false);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(ageLabel, gbc);
        gbc.gridx = 1;
        add(ageSpinner, gbc);

        // Listener to update age when DOB changes
        dobSpinner.addChangeListener(e -> updateAgeFromDOB());

        // Class label and combo box for class selection
        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(labelFont);

        classLabel.setToolTipText("Select the class of the student");
        String[] classes = {
                "Science 1", "Science 2", "Science 3",
                "Philosophy 1", "Philosophy 2", "Philosophy 3",
                "Mathematics 1", "Mathematics 2", "Mathematics 3"
        };
        classCombo = new JComboBox<>(classes);
        classCombo.setFont(fieldFont);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(classLabel, gbc);
        gbc.gridx = 1;
        add(classCombo, gbc);

        // Gender label and radio buttons for gender selection
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        genderLabel.setToolTipText("Select the gender of the student");

        maleButton = new JRadioButton("Male");
        maleButton.setBackground(new Color(230, 240, 255));

        femaleButton = new JRadioButton("Female");
        femaleButton.setBackground(new Color(230, 240, 255));

        otherButton = new JRadioButton("Other");
        otherButton.setBackground(new Color(230, 240, 255));

        maleButton.setFont(fieldFont);
        femaleButton.setFont(fieldFont);
        otherButton.setFont(fieldFont);

        // Group radio buttons so only one can be selected
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        // Panel to arrange gender radio buttons horizontally
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setBackground(new Color(230, 240, 255));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(otherButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(genderLabel, gbc);
        gbc.gridx = 1;
        add(genderPanel, gbc);

        // Add Student button to submit the form
        addButton = new JButton("Add Student");
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setToolTipText("Click to add the student to the database");

        // Panel to center the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.add(addButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Action listener for the Add Student button
        addButton.addActionListener(e -> addStudent());

        // Initialize age field based on current DOB
        updateAgeFromDOB();
    }

    /**
     * Calculates the age from the selected Date of Birth and updates the age spinner.
     */
    private void updateAgeFromDOB() {
        java.util.Date dob = (java.util.Date) dobSpinner.getValue();
        java.util.Calendar dobCal = java.util.Calendar.getInstance();
        dobCal.setTime(dob);
        java.util.Calendar today = java.util.Calendar.getInstance();

        int age = today.get(java.util.Calendar.YEAR) - dobCal.get(java.util.Calendar.YEAR);
        // Adjust age if birthday hasn't occurred yet this year
        if (today.get(java.util.Calendar.DAY_OF_YEAR) < dobCal.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--;
        }
        ageSpinner.setValue(Math.max(age, 0));
    }

    /**
     * Handles the Add Student button click event.
     * Validates input, creates a Student object, adds it to the database, and resets the form.
     */
    private void addStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        int age = (int) ageSpinner.getValue();
        String studentClass = (String) classCombo.getSelectedItem();
        String dob = ((JSpinner.DateEditor) dobSpinner.getEditor()).getFormat().format(dobSpinner.getValue());
        String gender = maleButton.isSelected() ? "Male"
                : femaleButton.isSelected() ? "Female"
                        : otherButton.isSelected() ? "Other" : "";

        // Validate required fields
        if (id.isEmpty() || name.isEmpty() || gender.isEmpty() || dob.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check for duplicate student ID
        if (StudentDatabase.getStudentById(id) != null) {
            JOptionPane.showMessageDialog(this, "Student ID already exists.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate age range
        if (age < 16 || age > 80) {
            JOptionPane.showMessageDialog(this, "Age must be between 16 and 80.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create new Student and add to database
        Student newStudent = new Student(id, name, gender, age, studentClass, dob);
        StudentDatabase.addStudent(newStudent);

        JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Reset form fields for next entry
        idField.setText("");
        nameField.setText("");
        dobSpinner.setValue(new java.util.Date());
        ageSpinner.setValue(18);
        classCombo.setSelectedIndex(0);
        genderGroup.clearSelection();
    }
}
