package ru.innopolis.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ru.innopolis.model.Account;
import ru.innopolis.model.Contact;
import ru.innopolis.model.ContactType;
import ru.innopolis.model.Group;
import ru.innopolis.model.Journal;
import ru.innopolis.model.Lector;
import ru.innopolis.model.Lesson;
import ru.innopolis.model.Student;
import ru.innopolis.view.R;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class InitialDataStore {

    private Random random = new Random();
    private List<Account> accounts = new ArrayList<>();
    private List<Lector> lectors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private Map<String, List<Lesson>> mapLessons = new LinkedHashMap<>();
    private List<Journal> journals = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();

    private static final String[] titles = {"Math", "Algorithms", "Android Programming"};

    private static InitialDataStore instance;

    private InitialDataStore() {}

    public static InitialDataStore getInstance() {
        if (instance == null) {
            synchronized (InitialDataStore.class) {
                if (instance == null) instance = new InitialDataStore();
            }
        }

        return instance;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Lector> getLectors() {
        return lectors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Map<String, List<Lesson>> getMapLessons() {
        return mapLessons;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void registerNewAccount(Account account) {
        accounts.add(account);
    }

    public void generateAll() {
        generateNewAccounts();
        generateNewLectors();
        generateNewStudents();
        generateNewLessons();
        generateNewGroups();
    }

    private void generateNewGroups() {

        Group math = new Group(titles[0]);
        math.setStudents(Arrays.asList(students.get(0), students.get(1), students.get(2)));

        updateGroupIdInLessons(math, mapLessons.get(titles[0]));
        math.setLessons(mapLessons.get(titles[0]));

        students.get(0).setGroupId(math.getGroupId());
        students.get(1).setGroupId(math.getGroupId());
        students.get(2).setGroupId(math.getGroupId());

        Group algo = new Group(titles[1]);
        algo.setStudents(Arrays.asList(students.get(3), students.get(4), students.get(5)));

        updateGroupIdInLessons(algo, mapLessons.get(titles[1]));
        algo.setLessons(mapLessons.get(titles[1]));

        students.get(3).setGroupId(algo.getGroupId());
        students.get(4).setGroupId(algo.getGroupId());
        students.get(5).setGroupId(algo.getGroupId());

        Group andro = new Group(titles[2]);
        andro.setStudents(Arrays.asList(students.get(6), students.get(7), students.get(8)));

        updateGroupIdInLessons(andro, mapLessons.get(titles[2]));
        andro.setLessons(mapLessons.get(titles[2]));

        students.get(6).setGroupId(andro.getGroupId());
        students.get(7).setGroupId(andro.getGroupId());
        students.get(8).setGroupId(andro.getGroupId());

        groups.add(math);
        groups.add(algo);
        groups.add(andro);
    }

    private void updateGroupIdInLessons(Group group, List<Lesson> lessons) {
        for (Lesson lesson : lessons) lesson.setGroupId(group.getGroupId());
    }

    private void generateNewLessons() {
        String[][] allSubjects = {
                {"Arithmetics", "Elementary Math", "Elementary Geometry", "Intro to Algebra", "Computational Math", "Discret Math"},
                {"Intro to Data Structure", "Advansed Data Structure", "Intro to Algorithms", "Algorithms in Practice", "Most common algorithms"},
                {"Introduction to Android", "Android UI", "Intro to Fragments", "Data Persistence in Android", "Networking"}
        };

        int counter = 0;
        for (String[] subjects : allSubjects) {
            List<Lesson> lessons = new ArrayList<>();
            int day = 1;

            for (String subject : subjects) {
                Lesson lesson = new Lesson(subject);

                Date date = new Date(2017, 6, day++);
                lesson.setDate(date);
                lesson.setLector(lectors.get(counter));

                if (counter == 0) {
                    lesson.setStudents(Arrays.asList(students.get(0), students.get(1), students.get(2)));
                } else if (counter == 1) {
                    lesson.setStudents(Arrays.asList(students.get(3), students.get(4), students.get(5)));
                } else if (counter == 2) {
                    lesson.setStudents(Arrays.asList(students.get(6), students.get(7), students.get(8)));
                }

                Journal journal = new Journal(date);
                journal.setLessonId(lesson.getLessonId());

                Map<Student, Boolean> visitors = new HashMap<>();
                int max = lesson.getStudents().size();
                for (int i = 0; i < max; i++) {
                    Student student = lesson.getStudents().get(i);
                    visitors.put(student, random.nextBoolean());
                }

                journal.setListeners(visitors);
                lesson.setJournal(journal);

                journals.add(journal);
                lessons.add(lesson);
            }

            mapLessons.put(titles[counter++], lessons);
        }
    }

    private void generateNewStudents() {

        Student std1 = new Student("John", "Doe", new Date(1975, 5, 19));
        Contact telegram = new Contact("@johny", ContactType.TELEGRAM, R.drawable.ic_telegram);
        Contact phone = new Contact("123456789", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts0 = new ArrayList<>(Arrays.asList(telegram, phone));

        std1.setContacts(contacts0);
        std1.setAccount(accounts.get(0));

        Student std2 = new Student("Jake", "Dilon", new Date(1985, 7, 9));
        telegram = new Contact("@jaly", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("85213654", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts1 = new ArrayList<>(Arrays.asList(telegram, phone));

        std2.setContacts(contacts1);
        std2.setAccount(accounts.get(1));

        Student std3 = new Student("Marry", "Kline", new Date(1978, 9, 7));
        telegram = new Contact("@marry", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("98785213", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts2 = new ArrayList<>(Arrays.asList(telegram, phone));

        std3.setContacts(contacts2);
        std3.setAccount(accounts.get(2));

        Student std4 = new Student("Manny", "Glin", new Date(1988, 9, 4));
        telegram = new Contact("@manny", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("9887444456", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts3 = new ArrayList<>(Arrays.asList(telegram, phone));

        std4.setContacts(contacts3);
        std4.setAccount(accounts.get(3));

        Student std5 = new Student("Marsy", "Gacho", new Date(1998, 3, 7));
        telegram = new Contact("@marsy", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("98785213457", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts4 = new ArrayList<>(Arrays.asList(telegram, phone));

        std5.setContacts(contacts4);
        std5.setAccount(accounts.get(4));

        Student std6 = new Student("Garry", "Hurry", new Date(1958, 9, 7));
        telegram = new Contact("@garry", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("98711213", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts5 = new ArrayList<>(Arrays.asList(telegram, phone));

        std6.setContacts(contacts5);
        std6.setAccount(accounts.get(5));

        Student std7 = new Student("Jane", "Ostin", new Date(1908, 9, 7));
        telegram = new Contact("@jane", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("9123511213", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts6 = new ArrayList<>(Arrays.asList(telegram, phone));

        std7.setContacts(contacts6);
        std7.setAccount(accounts.get(6));

        Student std8 = new Student("Sarra", "Connor", new Date(1958, 1, 7));
        telegram = new Contact("@sarra", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("+79272290858", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts7 = new ArrayList<>(Arrays.asList(telegram, phone));

        std8.setContacts(contacts7);
        std8.setAccount(accounts.get(7));

        Student std9 = new Student("Tarra", "Honnor", new Date(1958, 1, 9));
        telegram = new Contact("@tarra", ContactType.TELEGRAM, R.drawable.ic_telegram);
        phone = new Contact("550011213", ContactType.PHONE, R.drawable.ic_phone);

        List<Contact> contacts8 = new ArrayList<>(Arrays.asList(telegram, phone));

        std9.setContacts(contacts8);
        std9.setAccount(accounts.get(8));

        students.add(std1);
        students.add(std2);
        students.add(std3);
        students.add(std4);
        students.add(std5);
        students.add(std6);
        students.add(std7);
        students.add(std8);
        students.add(std9);
    }

    private void generateNewLectors() {
        Lector lector1 = new Lector("Jason", "McField");
        Contact phone = new Contact("1234567891", ContactType.PHONE, R.drawable.ic_phone);
        List<Contact> contacts = new ArrayList<>(Arrays.asList(phone));
        lector1.setContacts(contacts);

        Lector lector2 = new Lector("Patric", "Robertson");
        Contact phone2 = new Contact("7801456787", ContactType.PHONE, R.drawable.ic_phone);
        List<Contact> contacts2 = new ArrayList<>(Arrays.asList(phone2));
        lector2.setContacts(contacts2);

        Lector lector3 = new Lector("Mia", "Lue");
        Contact phone3 = new Contact("9801456789", ContactType.PHONE, R.drawable.ic_phone);
        List<Contact> contacts3 = new ArrayList<>(Arrays.asList(phone3));
        lector3.setContacts(contacts3);

        lectors.add(lector1);
        lectors.add(lector2);
        lectors.add(lector3);
    }

    private void generateNewAccounts() {
        Account account1 = new Account("john", "doe");
        Account account2 = new Account("jake", "dilon");
        Account account3 = new Account("marry", "kline");
        Account account4 = new Account("manny", "glin");
        Account account5 = new Account("marsy", "marsy");
        Account account6 = new Account("garry", "garry");
        Account account7 = new Account("jane", "jane");
        Account account8 = new Account("sarra", "sarra");
        Account account9 = new Account("tarra", "tarra");

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
        accounts.add(account5);
        accounts.add(account6);
        accounts.add(account7);
        accounts.add(account8);
        accounts.add(account9);
    }
}
