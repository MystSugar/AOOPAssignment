package assignment;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import java.awt.Font;
import javax.swing.*;

/**
 * ViewStudentsPanel is a JPanel that displays a welcome message and
 * instructions for using the Student Information System.
 */
public class ViewStudentsPanel extends JPanel {

    /**
     * Constructs the ViewStudentsPanel with a welcome label and info text area.
     */
    public ViewStudentsPanel() {
        // Set the layout manager with vertical gap of 30 pixels
        setLayout(new BorderLayout(0, 30));
        // Set the background color of the panel
        setBackground(new java.awt.Color(220, 240, 255));

        // Create and configure the welcome label
        JLabel welcomeLabel = new JLabel("🎓 Welcome to the Student Information System 🎓", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Set font style and size
        welcomeLabel.setForeground(new java.awt.Color(30, 60, 120)); // Set text color
        // Add padding above and below the label
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        // Add the label to the top (NORTH) of the panel
        add(welcomeLabel, NORTH);

        // Create and configure the info text area with instructions
        JTextArea infoText = new JTextArea(
                "✨ Use the tabs above to:\n\n"
                + "   • Add new students\n"
                + "   • Update student info or enter their grades\n"
                + "   • Look up students by ID to view their (confidential!) records\n"
                + "   • Delete entries when needed\n\n"
                + "💡 Grades are automatically calculated from midterm and final scores."
        );
        infoText.setEditable(false); // Make the text area read-only
        infoText.setLineWrap(true); // Enable line wrapping
        infoText.setWrapStyleWord(true); // Wrap at word boundaries
        infoText.setBackground(new java.awt.Color(245, 250, 255)); // Set background color
        infoText.setForeground(new java.awt.Color(40, 70, 120)); // Set text color
        // Add a compound border: a rounded line border and inner padding
        infoText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(180, 210, 240), 2, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        infoText.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Set font style and size
        // Add the text area to the center of the panel
        add(infoText, BorderLayout.CENTER);
    }
}
