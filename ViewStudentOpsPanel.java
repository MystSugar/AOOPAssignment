package AOOPAssignment;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import java.awt.Font;
import javax.swing.*;

/**
 * A JPanel that displays a welcome message and instructions
 * for using the Student Information System.
 */
public class ViewStudentOpsPanel extends JPanel {

    public ViewStudentOpsPanel() {
        setLayout(new BorderLayout(0, 30));
        setBackground(new java.awt.Color(220, 240, 255));

        // Create and configure the welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Student Information System!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(new java.awt.Color(30, 60, 120));
    
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        add(welcomeLabel, NORTH);

        // Create a non-editable text area with instructions
        JTextArea infoText = new JTextArea(
                " Use the tabs above to:\n\n"
                + "   • Add new students\n"
                + "   • Update student info or enter their grades\n"
                + "   • Look up students by ID to view their (confidential!) records\n"
                + "   • Delete entries when needed\n\n"
                + " - Grades are automatically calculated from midterm and final scores."
        );
        infoText.setEditable(false); 
        infoText.setLineWrap(true); 
        infoText.setWrapStyleWord(true); 

        // Set background and foreground colors for the text area
        infoText.setBackground(new java.awt.Color(245, 250, 255));
        infoText.setForeground(new java.awt.Color(40, 70, 120));

        infoText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(180, 210, 240), 2, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        infoText.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        
        // Add the instructions text area to the center of the panel
        add(infoText, BorderLayout.CENTER);
    }
}
