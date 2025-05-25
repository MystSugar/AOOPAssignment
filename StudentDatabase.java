package AOOPAssignment;

import java.io.*;
import java.util.ArrayList;

/**
 * StudentDatabase class manages a list of Student objects,
 * providing methods to add, update, delete, and retrieve students.
 */
public class StudentDatabase {

    private static final String FILE_NAME = "students.dat";
    private static ArrayList<Student> students = new ArrayList<>();

    static {
        loadFromFile();
    }

    /**
     * Adds a new student to the database and saves changes to file.
     * @param s the Student to add
     */
    public static void addStudent(Student s) {
        students.add(s);
        saveToFile();
    }

    /**
     * Retrieves a student by their ID.
     * @param id the student's ID
     * @return the Student object if found, otherwise null
     */
    public static Student getStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Saves the current list of students to the file using serialization.
     */
    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of students from the file using deserialization.
     */
    private static void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing student's information and saves changes to file.
     * @param s the Student object with updated information
     */
    public static void updateStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(s.getId())) {
                students.set(i, s);
                saveToFile();
                return;
            }
        }
    }

    /**
     * Deletes a student from the database by their ID and saves changes to file.
     * @param id the student's ID
     */
    public static void deleteStudent(String id) {
        students.removeIf(s -> s.getId().equals(id));
        saveToFile();
    }
}
