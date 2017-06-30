package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import ru.innopolis.model.Contact;
import ru.innopolis.model.ContactType;
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
    private Button backToGroup;

    private Student student;
    private Group group;
    private ContactListAdapter adapter;

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

        backToGroup = (Button) findViewById(R.id.backToGroup);
        backToGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StudentListActivity.class);
                intent.putExtra(StudentListActivity.GROUP, group);
                v.getContext().startActivity(intent);
            }
        });

        List<Contact> contacts = student.getContacts();
        adapter = new ContactListAdapter(this, contacts);

        ListView contactList = (ListView) findViewById(R.id.contactList);
        contactList.setAdapter(adapter);

        registerForContextMenu(contactList);

        if (group == null) return;

        JournalListAdapter journalListAdapter = new JournalListAdapter(this, group.getLessons(), student);
        ListView journalList = (ListView) findViewById(R.id.journalList);
        journalList.setAdapter(journalListAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Contact contact = (Contact) adapter.getItem(info.position);

        if (contact.getContactType() != ContactType.PHONE) return;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Contact contact = (Contact) adapter.getItem(info.position);

        if (contact.getContactType() != ContactType.PHONE) return false;

        switch (item.getItemId()) {
            case R.id.call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contact.getValue()));
                startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
