package AOOPAssignment;

import java.io.Serializable;

/**
 * The Grade class represents a student's grades for midterm and finals.
 * It provides methods to calculate the total score and determine the letter grade.
 */
public class Grade implements Serializable {

    // Fields to store midterm and finals scores
    private int midterm;
    private int finals;

    /**
     * Constructor to initialize midterm and finals scores.
     * @param midterm the midterm exam score
     * @param finals the final exam score
     */
    public Grade(int midterm, int finals) {
        this.midterm = midterm;
        this.finals = finals;
    }

    // Getter and setter for Midterm marks
    public int getMidterm() {
        return midterm;
    }
    public void setMidterm(int midterm) {
        this.midterm = midterm;
    }

    // Getter and setter for Finals marks
    public int getFinals() {
        return finals;
    }
    public void setFinals(int finals) {
        this.finals = finals;
    }

    /**
     * Calculates the total score based on weighted midterm (40%) and finals (60%).
     * @return the total weighted score, rounded to the nearest integer
     */
    public int getTotal() {
        return (int) Math.round(midterm * 0.4 + finals * 0.6);
    }

    /**
     * Determines the letter grade based on the total score.
     * @return the letter grade as a String ("A", "B", "C", "D", or "F")
     */
    public String getLetterGrade() {
        int total = getTotal();
        if (total >= 80) {
            return "A";
        } else if (total >= 60) {
            return "B";
        } else if (total >= 50) {
            return "C";
        } else if (total >= 40) {
            return "D";
        } else {
            return "F";
        }
    }

    /**
     * Returns a string representation of the Grade object,
     * including midterm, finals, total score, and letter grade.
     * @return formatted string with grade details
     */
    @Override
    public String toString() {
        return String.format("\n Midterm: %d, Finals: %d, \n Total: %d%%, \n Grade: %s\n",
                midterm, finals, getTotal(), getLetterGrade());
    }
}
