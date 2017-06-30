package ru.innopolis.manager;

import java.util.List;

import ru.innopolis.data.InitialDataStore;
import ru.innopolis.data.StudentDataStore;
import ru.innopolis.model.Account;
import ru.innopolis.model.Student;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class StudentManager {

    private static StudentDataStore studentDataStore = StudentDataStore.getInstance();

    public static Student findStudentByAccount(String username, String password) {
        return studentDataStore.findStudentByAccount(username, password);
    }

    public static List<Student> getAllStudents() {
        return studentDataStore.getAllStudents();
    }
}
