package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ru.innopolis.manager.AccountManager;
import ru.innopolis.manager.GroupManager;
import ru.innopolis.manager.InitialDataStoreManager;
import ru.innopolis.manager.StudentManager;
import ru.innopolis.model.Group;
import ru.innopolis.model.Student;

public class MainActivity extends Activity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button authBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialDataStoreManager.generateInitialData();

        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        authBtn = (Button) findViewById(R.id.authBtn);
        registerBtn = (Button) findViewById(R.id.regisBtn);
    }

    public void onLogin(View view) {
        String username = loginEditText.getText().toString();
        String userpass = passwordEditText.getText().toString();

        boolean valid = AccountManager.authenticate(username, userpass);

        if (username.equals("admin") && userpass.equals("admin")) {
            Intent intent = new Intent(this, CategoriesActivity.class);

            intent.putExtra(CategoriesActivity.USERNAME, username);
            intent.putParcelableArrayListExtra(CategoriesActivity.ALL_STUDENTS,
                    (ArrayList<? extends Parcelable>) StudentManager.getAllStudents());

            startActivity(intent);

        } else if (valid) {
            Student student = StudentManager.findStudentByAccount(username, userpass);
            Group group = GroupManager.findGroupById(student.getGroupId());

            Intent intent = new Intent(this, StudentDetailActivity.class);
            intent.putExtra(StudentDetailActivity.STUDENT, student);
            intent.putExtra(StudentDetailActivity.GROUP, group);

            startActivity(intent);

        } else {
            Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_LONG).show();
        }
    }

    public void onRegister(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}
