package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.innopolis.model.Account;
import ru.innopolis.utils.FakeDataGenerator;

/**
 * Created by ibrahim on 6/20/2017.
 */

public class RegistrationActivity extends Activity {

    private EditText username;
    private EditText password1;
    private EditText password2;
    private Button okBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = (EditText) findViewById(R.id.username);
        password1 = (EditText) findViewById(R.id.password1);
        password2 = (EditText) findViewById(R.id.password2);

        okBtn = (Button) findViewById(R.id.okBtn);
        cancelBtn = (Button) findViewById(R.id.cancel);
    }

    public void onOk(View view) {
        String user = username.getText().toString();
        String pass1 = password1.getText().toString();
        String pass2 = password2.getText().toString();

        if (!pass1.equals(pass2)) {
            Toast.makeText(this, "Password Error!", Toast.LENGTH_LONG).show();
            return;
        }

        boolean isOk = FakeDataGenerator.register(user, pass1);

        if (isOk) {
            Account account = new Account(user, pass1);

            Intent intent = new Intent(this, NewStudentActivity.class);
            intent.putExtra("ru.innopolis.model.Account", account);

            startActivity(intent);
        }
    }

    public void onCancel(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
