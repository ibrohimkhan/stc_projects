package ru.innopolis.view;

import android.app.Activity;
import android.os.Bundle;

import ru.innopolis.model.Account;
import ru.innopolis.model.Student;

public class NewStudentActivity extends Activity {
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        Bundle bundle = getIntent().getExtras();
        Account account = bundle.getParcelable("ru.innopolis.model.Account");

        student = new Student(account);
    }
}
