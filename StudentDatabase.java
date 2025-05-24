package assignment;

import java.io.*;
import java.util.ArrayList;

/**
 * StudentDatabase manages a list of Student objects, providing methods to add,
 * update, retrieve, and delete students. The list is persisted to a file using
 * Java serialization.
 */
public class StudentDatabase {

    // Name of the file where student data is stored
    private static final String FILE_NAME = "students.dat";
    // In-memory list of students
    private static ArrayList<Student> students = new ArrayList<>();

    // Static block to load students from file when the class is first loaded
    static {
        loadFromFile();
    }

    /**
     * Adds a new student to the database and saves the updated list to file.
     *
     * @param s the Student to add
     */
    public static void addStudent(Student s) {
        students.add(s);
        saveToFile();
    }

    /**
     * Retrieves a student by their ID.
     *
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
     * Loads the list of students from the file using deserialization. If the
     * file does not exist or an error occurs, the list remains empty.
     */
    private static void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Print stack trace if loading fails (e.g., file not found on first run)
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing student's information and saves the changes to file.
     *
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
     * Deletes a student by their ID and saves the updated list to file.
     *
     * @param id the student's ID
     */
    public static void deleteStudent(String id) {
        students.removeIf(s -> s.getId().equals(id));
        saveToFile();
    }
}
