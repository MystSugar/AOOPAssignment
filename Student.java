package assignment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a student with personal details and a record of grades.
 */
public class Student implements Serializable {

    // Unique identifier for the student (immutable)
    private final String id;
    // Student's name
    private String name;
    // Student's gender
    private String gender;
    // Student's age
    private int age;
    // Student's class (e.g., "10A")
    private String studentClass;
    // Student's date of birth
    private String dob;
    // Map of subject names to Grade objects
    private Map<String, Grade> grades;

    /**
     * Constructs a new Student with the given details.
     *
     * @param id Unique student ID
     * @param name Student's name
     * @param gender Student's gender
     * @param age Student's age
     * @param studentClass Student's class
     * @param dob Student's date of birth
     */
    public Student(String id, String name, String gender, int age, String studentClass, String dob) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.studentClass = studentClass;
        this.dob = dob;
        this.grades = new HashMap<>();
    }

    /**
     * Gets the student's unique ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the student's gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the student's gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the student's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the student's age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the student's class.
     */
    public String getStudentClass() {
        return studentClass;
    }

    /**
     * Sets the student's class.
     */
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    /**
     * Gets the student's date of birth.
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the student's date of birth.
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Gets the grade for a specific subject.
     *
     * @param subject The subject name
     * @return The Grade object for the subject, or null if not found
     */
    public Grade getGrade(String subject) {
        return grades.get(subject);
    }

    /**
     * Sets the grade for a specific subject.
     *
     * @param subject The subject name
     * @param grade The Grade object to associate with the subject
     */
    public void setGrade(String subject, Grade grade) {
        grades.put(subject, grade);
    }

    /**
     * Gets all grades for the student.
     *
     * @return Map of subject names to Grade objects
     */
    public Map<String, Grade> getAllGrades() {
        return grades;
    }

    /**
     * Returns a string representation of the student.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Gender: " + gender + ", Age: " + age + ", Class: " + studentClass + ", DOB: " + dob;
    }

}
