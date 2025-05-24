package assignment;

import java.awt.*;
import javax.swing.*;

/**
 * A JPanel for adding a new student. Provides fields for ID, name, age, class,
 * gender, and date of birth. Validates input and adds the student to the
 * StudentDatabase.
 */
public class AddStudentPanel extends JPanel {

    // --- UI Components ---
    private JTextField idField, nameField; // Text fields for ID, name
    private JSpinner ageSpinner, dobSpinner; // Spinner for age selection
    private JComboBox<String> classCombo; // Dropdown for class selection
    private JRadioButton maleButton, femaleButton, otherButton; // Radio buttons for gender
    private ButtonGroup genderGroup; // Group for gender radio buttons
    private JButton addButton; // Button to add student

    /**
     * Constructs the AddStudentPanel and initializes all UI components.
     */
    public AddStudentPanel() {
        // Set layout and panel appearance
        setLayout(new GridBagLayout());
        setBackground(new Color(230, 240, 255));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Padding between components
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // --- Student ID ---
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

        // --- Name ---
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

        // --- Age (Spinner) ---
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        ageLabel.setToolTipText("Select the age of the student");
        // Spinner with min=16, max=80, step=1, initial=18
        ageSpinner = new JSpinner(new SpinnerNumberModel(18, 16, 80, 1));
        ((JSpinner.DefaultEditor) ageSpinner.getEditor()).getTextField().setFont(fieldFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(ageLabel, gbc);
        gbc.gridx = 1;
        add(ageSpinner, gbc);

        // --- Class (Dropdown) ---
        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(labelFont);
        classLabel.setToolTipText("Select the class of the student");
        // List of available classes
        String[] classes = {
            "Science 1", "Science 2", "Science 3",
            "Philosophy 1", "Philosophy 2", "Philosophy 3",
            "Mathematics 1", "Mathematics 2", "Mathematics 3"
        };
        classCombo = new JComboBox<>(classes);
        classCombo.setFont(fieldFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(classLabel, gbc);
        gbc.gridx = 1;
        add(classCombo, gbc);

        // --- Gender (Radio Buttons) ---
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        genderLabel.setToolTipText("Select the gender of the student");
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        otherButton = new JRadioButton("Other");
        maleButton.setFont(fieldFont);
        femaleButton.setFont(fieldFont);
        otherButton.setFont(fieldFont);

        // Group radio buttons so only one can be selected
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        // Panel to hold gender radio buttons horizontally
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setOpaque(false);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(otherButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(genderLabel, gbc);
        gbc.gridx = 1;
        add(genderPanel, gbc);

        // --- Date of Birth ---
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(labelFont);

        // Use JSpinner with SpinnerDateModel for date picking
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dobSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "dd/MM/yyyy");
        dobSpinner.setEditor(dateEditor);
        dobSpinner.setFont(fieldFont);
        dobSpinner.setToolTipText("Pick the date of birth (DD/MM/YYYY)");

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(dobLabel, gbc);
        gbc.gridx = 1;
        add(dobSpinner, gbc);

        // --- Add Button ---
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

        // Add action listener to handle adding a student when button is clicked
        addButton.addActionListener(e -> addStudent());
    }

    /**
     * Validates input fields and adds a new student to the database. Shows
     * error dialogs for invalid input.
     */
    private void addStudent() {
        // --- Gather input values ---
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        int age = (int) ageSpinner.getValue();
        String studentClass = (String) classCombo.getSelectedItem();
        String dob = ((JSpinner.DateEditor) dobSpinner.getEditor()).getFormat().format(dobSpinner.getValue());
        // Determine selected gender
        String gender = maleButton.isSelected() ? "Male"
                : femaleButton.isSelected() ? "Female"
                : otherButton.isSelected() ? "Other" : "";

        // --- Input Validation ---
        // Check for empty fields
        if (id.isEmpty() || name.isEmpty() || gender.isEmpty() || dob.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check for duplicate student ID
        if (StudentDatabase.getStudentById(id) != null) {
            JOptionPane.showMessageDialog(this, "Student ID already exists.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check age range
        if (age < 16 || age > 80) {
            JOptionPane.showMessageDialog(this, "Age must be between 16 and 80.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- Add Student to Database ---
        // Create new Student object and add to database
        Student newStudent = new Student(id, name, gender, age, studentClass, dob);
        StudentDatabase.addStudent(newStudent);

        // Show success message
        JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // --- Reset Fields ---
        // Clear all input fields for next entry
        idField.setText("");
        nameField.setText("");
        dobSpinner.setValue(new java.util.Date());
        ageSpinner.setValue(18);
        classCombo.setSelectedIndex(0);
        genderGroup.clearSelection();
    }
}
