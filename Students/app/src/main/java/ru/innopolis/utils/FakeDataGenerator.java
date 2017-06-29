package ru.innopolis.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ru.innopolis.model.Contact;
import ru.innopolis.model.ContactType;
import ru.innopolis.model.Group;
import ru.innopolis.model.Journal;
import ru.innopolis.model.Lector;
import ru.innopolis.model.Lesson;
import ru.innopolis.model.Student;
import ru.innopolis.view.R;

/**
 * Created by ibrahim on 6/20/2017.
 */

public class FakeDataGenerator {

    public static Map<String, String> users = new HashMap<>();
    public static List<Student> students = new ArrayList<>();

    public static boolean authenticate(String username, String userpass) {
        String password = users.get(username);
        if (password == null) return false;
        return password.equals(userpass);
    }

    public static boolean register(String user, String pass1) {
        users.put(user, pass1);
        return true;
    }

    public static void createStudents() {
        Student std1 = new Student("John", "Doe", new Date(1975, 5, 19));
        Contact telegram = new Contact("@johny", ContactType.TELEGRAM, R.drawable.ic_telegram);
        Contact phone = new Contact("123456789", ContactType.PHONE, R.drawable.ic_phone);
        List<Contact> contacts = Arrays.asList(telegram, phone);
        std1.setContacts(contacts);

        users.put("john", "doe");
        students.add(std1);

        Student std2 = new Student("Jake", "Dilon", new Date(1985, 7, 9));
        telegram = new Contact("@jaly", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("85213654", ContactType.PHONE, R.drawable.ic_phone);
        contacts = Arrays.asList(telegram, phone);
        std2.setContacts(contacts);

        users.put("jake", "dilon");
        students.add(std2);

        Student std3 = new Student("Marry", "Kline", new Date(1978, 9, 7));
        telegram = new Contact("@marry", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("98785213", ContactType.PHONE, R.drawable.ic_phone);
        contacts = Arrays.asList(telegram, phone);
        std3.setContacts(contacts);

        users.put("marry", "kline");
        students.add(std3);

        Student std4 = new Student("Manny", "Glin", new Date(1988, 9, 4));
        telegram = new Contact("@manny", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("9887444456", ContactType.PHONE, R.drawable.ic_phone);
        contacts = Arrays.asList(telegram, phone);
        std4.setContacts(contacts);

        users.put("manny", "glin");
        students.add(std4);

        Student std5 = new Student("Marsy", "Gacho", new Date(1998, 3, 7));
        telegram = new Contact("@marsy", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("98785213457", ContactType.PHONE, R.drawable.ic_phone);
        contacts = Arrays.asList(telegram, phone);
        std5.setContacts(contacts);

        users.put("marsy", "marsy");
        students.add(std5);

        Student std6 = new Student("Garry", "Hurry", new Date(1958, 9, 7));
        telegram = new Contact("@garry", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("98711213", ContactType.PHONE, R.drawable.ic_phone);
        contacts = Arrays.asList(telegram, phone);
        std6.setContacts(contacts);

        users.put("garry", "garry");
        students.add(std6);

        Student std7 = new Student("Jane", "Ostin", new Date(1908, 9, 7));
        telegram = new Contact("@jane", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("9123511213", ContactType.PHONE, R.drawable.ic_phone);
        contacts = Arrays.asList(telegram, phone);
        std7.setContacts(contacts);

        users.put("jane", "jane");
        students.add(std7);

        Student std8 = new Student("Sarra", "Connor", new Date(1958, 1, 7));
        telegram = new Contact("@sarra", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("+79272290858", ContactType.PHONE, R.drawable.ic_phone);
        contacts = Arrays.asList(telegram, phone);
        std8.setContacts(contacts);

        users.put("sarra", "sarra");
        students.add(std8);

        Student std9 = new Student("Tarra", "Honnor", new Date(1958, 1, 9));
        telegram = new Contact("@tarra", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("550011213", ContactType.PHONE, R.drawable.ic_phone);
        contacts = Arrays.asList(telegram, phone);
        std9.setContacts(contacts);

        users.put("tarra", "tarra");
        students.add(std9);
    }

    public static List<Group> createGroup() {

        List<Student> std1 = Arrays.asList(students.get(0), students.get(3), students.get(1), students.get(5));
        List<Student> std2 = Arrays.asList(students.get(2), students.get(4), students.get(6), students.get(7));
        List<Student> std3 = Arrays.asList(students.get(8), students.get(7), students.get(5), students.get(6));

        List<List<Lesson>> allLessons = createLessons(std1, std2, std3);

        List<Group> groups = new ArrayList<>();
        Group gr1 = new Group("Math", std1, allLessons.get(0));
        addStudentsToGroup(std1, gr1);

        Group gr2 = new Group("Algorithms", std2, allLessons.get(1));
        addStudentsToGroup(std2, gr2);

        Group gr3 = new Group("Android Programming", std3, allLessons.get(2));
        addStudentsToGroup(std3, gr3);

        groups.add(gr1);
        groups.add(gr2);
        groups.add(gr3);

        return groups;
    }

    private static void addStudentsToGroup(List<Student> students, Group group) {
        for (Student student : students) {
            student.setGroupId(group.getGroupId());
        }
    }

    public static List<List<Lesson>> createLessons(List<Student> std1, List<Student> std2, List<Student> std3) {
        List<List<Lesson>> allLessons = new ArrayList<>();

        String[][] allSubjects = {
                {"Arithmetics", "Elementary Math", "Elementary Geometry", "Intro to Algebra", "Computational Math", "Discret Math"},
                {"Intro to Data Structure", "Advansed Data Structure", "Intro to Algorithms", "Algorithms in Practice", "Most common algorithms"},
                {"Introduction to Android", "Android UI", "Intro to Fragments", "Data Persistence in Android", "Networking"}
        };

        String[] lectorsName = {"Jason McField", "Patric Robertson", "Mia Lue"};

        Contact phone = new Contact("123456789", ContactType.PHONE, R.drawable.ic_phone);
        List<Contact> contacts = Arrays.asList(phone);

        int counter = 0;
        for (String[] subjects : allSubjects) {
            List<Lesson> lessons = new ArrayList<>();
            int day = 1;

            for (String subject : subjects) {
                Lesson lesson = new Lesson(subject);

                Lector lector = new Lector(lectorsName[counter].split(" ")[0], lectorsName[counter].split(" ")[1]);
                lector.setContacts(contacts);
                lesson.setLector(lector);

                Date date = new Date(2017, 6, day++);

                lesson.setDate(date);
                if (counter == 0) lesson.setStudents(std1);
                else if (counter == 1) lesson.setStudents(std2);
                else if (counter == 2) lesson.setStudents(std3);

                Journal journal = new Journal(date);
                journal.setLessonId(lesson.getLessonId());

                Map<Student, Boolean> visitors = new HashMap<>();
                for (int i = 0; i < lesson.getStudents().size(); i++) {
                    Student student = lesson.getStudents().get(i);
                    if (i % 2 == 0) visitors.put(student, true);
                    else visitors.put(student, false);
                }

                journal.setListeners(visitors);

                lesson.setJournal(journal);
                lessons.add(lesson);
            }

            counter++;
            allLessons.add(lessons);
        }

        return allLessons;
    }
}
