package ru.innopolis.justchat.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ru.innopolis.justchat.MainActivity;
import ru.innopolis.justchat.R;
import ru.innopolis.justchat.manager.AccountManager;
import ru.innopolis.justchat.model.RegistrationForm;
import ru.innopolis.justchat.model.User;

public class RegistrationActivity extends Activity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password1;
    private EditText password2;

    private Button ok;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName  = (EditText) findViewById(R.id.lastName);
        email     = (EditText) findViewById(R.id.email);
        password1 = (EditText) findViewById(R.id.password1);
        password2 = (EditText) findViewById(R.id.password2);

        ok      = (Button) findViewById(R.id.register);
        cancel  = (Button) findViewById(R.id.cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!password1.getText().toString().equals(password2.getText().toString())) return;

                RegistrationForm form = new RegistrationForm(firstName.getText().toString(),
                                                             lastName.getText().toString(),
                                                             email.getText().toString(),
                                                             password1.getText().toString());

                User user = null;
                try {
                    user = registration.execute(form).get();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (user == null) Toast.makeText(RegistrationActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(RegistrationActivity.this, ConfigurationActivity.class);
                    intent.putExtra(ConfigurationActivity.USER, user);
                    startActivity(intent);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    AsyncTask<RegistrationForm, Void, User> registration = new AsyncTask<RegistrationForm, Void, User>() {
        @Override
        protected User doInBackground(RegistrationForm... forms) {
            return AccountManager.createNewUser(forms[0]);
        }
    };
}
