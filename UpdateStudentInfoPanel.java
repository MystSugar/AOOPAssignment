package AOOPAssignment;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel for updating student information.
 * Displays fields for editing student details and handles update logic.
 */
public class UpdateStudentInfoPanel extends JPanel {

    // UI components for student fields
    private JTextField idField, nameField;
    private JSpinner ageSpinner, dobSpinner;
    private JComboBox<String> classCombo;
    private JRadioButton maleButton, femaleButton, otherButton;
    private ButtonGroup genderGroup;
    private JButton updateButton, backButton;
    private Student currentStudent; // The student being edited

    /**
     * Constructs the panel and initializes UI with the given student data.
     * @param student The student whose info is to be updated.
     */
    public UpdateStudentInfoPanel(Student student) {
        this.currentStudent = student;
        setLayout(new GridBagLayout());
        setBackground(new Color(230, 240, 255));
        setBorder(new EmptyBorder(30, 40, 30, 40)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Student ID (not editable)
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(labelFont);

        idField = new JTextField(student.getId(), 15);
        idField.setFont(fieldFont);
        idField.setEditable(false); // ID cannot be changed
        idField.setToolTipText("Student ID cannot be edited.");
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        // Student Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);

        nameField = new JTextField(15);
        nameField.setFont(fieldFont);
        nameField.setText(student.getName());
        nameField.setToolTipText("Enter the full name of the student");

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Age (Non-editable, calculated from DOB)
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);

        ageSpinner = new JSpinner(new SpinnerNumberModel(student.getAge(), 16, 80, 1));
        ((JSpinner.DefaultEditor) ageSpinner.getEditor()).getTextField().setFont(fieldFont);
        ((JSpinner.DefaultEditor) ageSpinner.getEditor()).getTextField().setDisabledTextColor(Color.BLACK);
        ageSpinner.setToolTipText("Age is calculated from Date of Birth and cannot be edited directly.");
        ageSpinner.setEnabled(false); // Age is not directly editable

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(ageLabel, gbc);
        gbc.gridx = 1;
        add(ageSpinner, gbc);

        // Class selection (Dropdown)
        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(labelFont);

        String[] classes = {
            "Science 1", "Science 2", "Science 3",
            "Philosophy 1", "Philosophy 2", "Philosophy 3",
            "Mathematics 1", "Mathematics 2", "Mathematics 3"
        };
        classCombo = new JComboBox<>(classes);
        classCombo.setSelectedItem(student.getStudentClass());
        classCombo.setFont(fieldFont);
        classCombo.setToolTipText("Select the class of the student");

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(classLabel, gbc);
        gbc.gridx = 1;
        add(classCombo, gbc);

        // Gender selection (Radio buttons)
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);

        maleButton = new JRadioButton("Male");
        maleButton.setBackground(new Color(230, 240, 255));

        femaleButton = new JRadioButton("Female");
        femaleButton.setBackground(new Color(230, 240, 255));

        otherButton = new JRadioButton("Other");
        otherButton.setBackground(new Color(230, 240, 255));

        maleButton.setFont(fieldFont);
        femaleButton.setFont(fieldFont);
        otherButton.setFont(fieldFont);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setBackground(new Color(230, 240, 255));
        genderPanel.setOpaque(false);
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

        // Date of Birth field (editable, affects age)
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(labelFont);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        dobSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "dd/MM/yyyy");
        dobSpinner.setEditor(dateEditor);
        dobSpinner.setFont(fieldFont);
        dobSpinner.setToolTipText("Select the date of birth of the student");

        // Set DOB spinner value from student data, fallback to today if parsing fails
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dobSpinner.setValue(sdf.parse(student.getDob()));
        } catch (ParseException e) {
            dobSpinner.setValue(new java.util.Date());
        }

        // Listener to update age when DOB changes
        dobSpinner.addChangeListener(e -> updateAgeFromDOB());

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(dobLabel, gbc);
        gbc.gridx = 1;
        add(dobSpinner, gbc);

        // Update and Back buttons
        updateButton = new JButton("Update Info");
        backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Button actions
        updateButton.addActionListener(e -> updateStudent());
        backButton.addActionListener(e -> goBack());
        updateAgeFromDOB(); // Initialize age field based on DOB
    }

    /**
     * Calculates and updates the age field based on the selected date of birth.
     */
    private void updateAgeFromDOB() {
        java.util.Date dob = (java.util.Date) dobSpinner.getValue();
        java.util.Calendar dobCal = java.util.Calendar.getInstance();
        dobCal.setTime(dob);
        java.util.Calendar today = java.util.Calendar.getInstance();

        int age = today.get(java.util.Calendar.YEAR) - dobCal.get(java.util.Calendar.YEAR);
        // Adjust if birthday hasn't occurred yet this year
        if (today.get(java.util.Calendar.DAY_OF_YEAR) < dobCal.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--;
        }
        ageSpinner.setValue(Math.max(age, 0));
    }

    /**
     * Validates input, updates the student object, and saves changes to the database.
     * Shows appropriate messages for success or errors.
     */
    private void updateStudent() {
        String name = nameField.getText().trim();
        int age = (int) ageSpinner.getValue();
        String studentClass = (String) classCombo.getSelectedItem();
        // Determine selected gender
        String gender = maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : otherButton.isSelected() ? "Other" : "";

        java.util.Date dobDate = (java.util.Date) dobSpinner.getValue();
        SimpleDateFormat sdfDob = new SimpleDateFormat("dd/MM/yyyy");
        String dob = sdfDob.format(dobDate);
        
        // Validate required fields
        if (name.isEmpty() || gender.isEmpty() || dob.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
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

        // Save changes to database
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
