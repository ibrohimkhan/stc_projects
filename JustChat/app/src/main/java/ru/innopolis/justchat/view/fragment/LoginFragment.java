package ru.innopolis.justchat.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ru.innopolis.justchat.R;
import ru.innopolis.justchat.manager.AccountManager;
import ru.innopolis.justchat.model.User;
import ru.innopolis.justchat.view.RegistrationActivity;
import ru.innopolis.justchat.view.UserConfigurationActivity;

/**
 * Created by ibrahim on 7/7/2017.
 */

public class LoginFragment extends Fragment {

    private EditText email;
    private EditText password;

    private Button login;
    private Button register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);

        login = (Button) view.findViewById(R.id.login);
        register = (Button) view.findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                if (userEmail == null || userEmail.isEmpty() || userPassword == null || userPassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Fill out all fields correctly, please!", Toast.LENGTH_LONG).show();
                    return;
                }

                User user = null;
                try {
                    user = authentication.execute(userEmail, userPassword).get();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (user == null) Toast.makeText(getActivity(), "Something went wrong :(", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getActivity(), UserConfigurationActivity.class);
                    intent.putExtra(UserConfigurationActivity.USER, user);
                    startActivity(intent);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    AsyncTask<String, Void, User> authentication = new AsyncTask<String, Void, User>() {
        @Override
        protected User doInBackground(String... credentials) {
            return AccountManager.authenticate(credentials[0], credentials[1]);
        }
    };
}
