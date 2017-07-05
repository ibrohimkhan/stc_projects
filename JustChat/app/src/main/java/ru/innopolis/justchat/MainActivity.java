package ru.innopolis.justchat;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ru.innopolis.justchat.manager.AccountManager;
import ru.innopolis.justchat.model.User;
import ru.innopolis.justchat.view.ConfigurationActivity;
import ru.innopolis.justchat.view.RegistrationActivity;

public class MainActivity extends Activity {

    private EditText email;
    private EditText password;

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                User user = null;
                try {
                    user = authentication.execute(userEmail, userPassword).get();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (user == null) Toast.makeText(MainActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                    intent.putExtra(ConfigurationActivity.USER, user);
                    startActivity(intent);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    AsyncTask<String, Void, User> authentication = new AsyncTask<String, Void, User>() {
        @Override
        protected User doInBackground(String... credentials) {
            return AccountManager.authenticate(credentials[0], credentials[1]);
        }
    };
}
