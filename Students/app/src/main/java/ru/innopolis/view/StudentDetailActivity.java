package ru.innopolis.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import ru.innopolis.model.Contact;
import ru.innopolis.model.Group;
import ru.innopolis.model.Student;
import ru.innopolis.view.adapter.ContactListAdapter;
import ru.innopolis.view.adapter.JournalListAdapter;

/**
 * Created by ibrahim on 6/20/2017.
 */

public class StudentDetailActivity extends Activity {
    public static final String STUDENT = "student";
    public static final String GROUP = "group";

    private EditText firstName;
    private EditText lastName;

    private Student student;
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        student = (Student) getIntent().getParcelableExtra(STUDENT);
        group = (Group) getIntent().getParcelableExtra(GROUP);

        firstName = (EditText) findViewById(R.id.firstName);
        firstName.setText(student.getFirstName());

        lastName = (EditText) findViewById(R.id.lastName);
        lastName.setText(student.getLastName());

        List<Contact> contacts = student.getContacts();
        ContactListAdapter adapter = new ContactListAdapter(this, contacts);

        ListView contactList = (ListView) findViewById(R.id.contactList);
        contactList.setAdapter(adapter);

        if (group == null) return;

        JournalListAdapter journalListAdapter = new JournalListAdapter(this, group.getLessons(), student);
        ListView journalList = (ListView) findViewById(R.id.journalList);
        journalList.setAdapter(journalListAdapter);
    }
}
