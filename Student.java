package AOOPAssignment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a student with personal details and grades.
 * Implements Serializable for object serialization.
 */
public class Student implements Serializable {

    // Unique identifier for the student (immutable)
    private final String id;
    private String name;
    private String gender;
    private int age;
    private String studentClass;
    private String dob;
    private Map<String, Grade> grades;

    /**
     * Constructs a new Student with the given details.
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

    // Getter for student ID
    public String getId() {
        return id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for gender
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and setter for age
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
    // Getter and setter for student class
    public String getStudentClass() {
        return studentClass;
    }
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    // Getter and setter for date of birth
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Retrieves the grade for a specific subject.
     * @param subject The subject name
     * @return Grade object for the subject
     */
    public Grade getGrade(String subject) {
        return grades.get(subject);
    }

    /**
     * Sets the grade for a specific subject.
     * @param subject The subject name
     * @param grade The Grade object to set
     */
    public void setGrade(String subject, Grade grade) {
        grades.put(subject, grade);
    }

    /**
     * Returns all grades for the student.
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
