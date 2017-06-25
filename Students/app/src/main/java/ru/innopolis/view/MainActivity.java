package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.innopolis.utils.FakeDataGenerator;

public class MainActivity extends Activity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button authBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FakeDataGenerator.createStudents();

        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        authBtn = (Button) findViewById(R.id.authBtn);
        registerBtn = (Button) findViewById(R.id.regisBtn);
    }

    public void onLogin(View view) {
        String username = loginEditText.getText().toString();
        String userpass = passwordEditText.getText().toString();

        boolean valid = FakeDataGenerator.authenticate(username, userpass);

        if (valid) {
            Intent intent = new Intent(this, GroupActivity.class);
            intent.putExtra(GroupActivity.USERNAME, username);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_LONG).show();
        }
    }

    public void onRegister(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}
