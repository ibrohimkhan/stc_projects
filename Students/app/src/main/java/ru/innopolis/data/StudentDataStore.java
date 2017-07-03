package ru.innopolis.data;

import java.util.List;

import ru.innopolis.model.Account;
import ru.innopolis.model.Student;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class StudentDataStore {

    private InitialDataStore dataStore = InitialDataStore.getInstance();
    private AccountDataStore accountDataStore = AccountDataStore.getInstance();

    private StudentDataStore() {}

    private static class Helper {
        private static final StudentDataStore INSTANCE = new StudentDataStore();
    }

    public static StudentDataStore getInstance() {
        return Helper.INSTANCE;
    }

    public Student findStudentByAccount(String username, String password) {
        Account account = accountDataStore.findAccount(username, password);
        Student student = findStudentByAccount(account);

        return student;
    }

    private Student findStudentByAccount(Account account) {
        List<Student> students = dataStore.getStudents();

        for (Student student : students) {
            if (student.getAccount().equals(account)) return student;
        }

        return null;
    }

    public List<Student> getAllStudents() {
        return dataStore.getStudents();
    }
}
