package AOOPAssignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A JPanel that allows searching for a student by ID and displaying either their information or grades.
 */
public class SearchStudentPanel extends JPanel {

    private JTextField idField;      
    private JTextArea resultArea;    
    private JRadioButton infoRadio, gradesRadio;  
    private JButton searchButton;    

    /**
     * Constructs the search panel UI and sets up event handling.
     */
    public SearchStudentPanel() {
        setLayout(new GridBagLayout()); 
        setBackground(new Color(230, 240, 255));
        setForeground(new java.awt.Color(0, 0, 0));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Radio buttons for selection (Student Info or Grades) 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        infoRadio = new JRadioButton("Student Information");
        gradesRadio = new JRadioButton("Grades");
        infoRadio.setFont(fieldFont);
        gradesRadio.setFont(fieldFont);
        infoRadio.setBackground(new Color(230, 240, 255));
        gradesRadio.setBackground(new Color(230, 240, 255));

        // Group radio buttons so only one can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(infoRadio);
        group.add(gradesRadio);
        infoRadio.setSelected(true); // Default selection

        // Panel to hold radio buttons
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radioPanel.setBackground(new Color(230, 240, 255));
        radioPanel.add(infoRadio);
        radioPanel.add(Box.createHorizontalStrut(20));
        radioPanel.add(gradesRadio);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(radioPanel, gbc);

        // Label for student ID input 
        JLabel idLabel = new JLabel("Enter Student ID:");
        idLabel.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(idLabel, gbc);

        // Text field for entering student ID
        idField = new JTextField(18);
        idField.setFont(fieldFont);
        idField.setToolTipText("Enter the unique ID of the student to search");

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0; 
        add(idField, gbc);
        gbc.weightx = 0; 

        // Search button 
        searchButton = new JButton("Search Student");
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setToolTipText("Click to search for the student in the database");

        // Panel to center the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.add(searchButton);

        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Area to display search results, inside a scroll pane
        resultArea = new JTextArea(28, 70); 
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 13)); 

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Add action listener to search button 
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent(); 
            }
        });
    }

    /**
     * Searches for a student by ID and displays either their information or grades.
     */
    private void searchStudent() {
        String id = idField.getText().trim();
        resultArea.setText(""); 

        if (id.isEmpty()) {
            // Show error if ID field is empty
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve student from database
        Student s = StudentDatabase.getStudentById(id);
        if (s != null) {
            StringBuilder sb = new StringBuilder();
            if (infoRadio.isSelected()) {
                // Display student information
                sb.append(" ID: ").append(s.getId()).append("\n");
                sb.append(" Name: ").append(s.getName()).append("\n");
                sb.append(" Gender: ").append(s.getGender()).append("\n");
                sb.append(" Age: ").append(s.getAge()).append("\n");
                sb.append(" Class: ").append(s.getStudentClass()).append("\n");
                sb.append(" DOB: ").append(s.getDob()).append("\n");
            } else if (gradesRadio.isSelected()) {
                // Display student grades for each subject
                String[] subjects = {"Maths", "Science", "English"};
                sb.append("-----------------------------\n");
                for (String subject : subjects) {
                    sb.append(" ").append(subject).append(":");
                    Grade grade = s.getGrade(subject);
                
                    if (grade != null) {
                        sb.append(" ").append(grade.toString());
                    } else {
                        sb.append(" No grade available\n");
                    }
                    sb.append("-----------------------------\n");
                }
            }
            resultArea.setText(sb.toString()); // Show results
        } else {
            // Student not found
            resultArea.setText("Student not found."); 
        }
    }
}
