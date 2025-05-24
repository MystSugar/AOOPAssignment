package assignment;

import java.io.Serializable;

// The Grade class represents a student's grades for midterm and finals, and calculates weighted total and letter grade.
public class Grade implements Serializable {

    // Fields to store midterm and finals scores
    private int midterm;
    private int finals;

    // Constructor to initialize midterm and finals scores
    public Grade(int midterm, int finals) {
        this.midterm = midterm;
        this.finals = finals;
    }

    // Getter for midterm score
    public int getMidterm() {
        return midterm;
    }

    // Setter for midterm score
    public void setMidterm(int midterm) {
        this.midterm = midterm;
    }

    // Getter for finals score
    public int getFinals() {
        return finals;
    }

    // Setter for finals score
    public void setFinals(int finals) {
        this.finals = finals;
    }

    // Calculates the weighted total score (midterm 40%, finals 60%)
    public int getTotal() {
        return (int) Math.round(midterm * 0.4 + finals * 0.6);
    }

    // Determines the letter grade based on the weighted total
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

    // Returns a string representation of the Grade object
    @Override
    public String toString() {
        return String.format("Midterm: %d, Finals: %d, \nTotal: %d%%, \nGrade: %s",
                midterm, finals, getTotal(), getLetterGrade());
    }
}
