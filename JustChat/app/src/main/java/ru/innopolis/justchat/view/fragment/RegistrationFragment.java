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
import ru.innopolis.justchat.model.RegistrationForm;
import ru.innopolis.justchat.model.User;
import ru.innopolis.justchat.view.MainActivity;
import ru.innopolis.justchat.view.UserConfigurationActivity;

/**
 * Created by ibrahim on 7/7/2017.
 */

public class RegistrationFragment extends Fragment {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password1;
    private EditText password2;

    private Button ok;
    private Button cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_fragment, container, false);

        firstName = (EditText) view.findViewById(R.id.firstName);
        lastName  = (EditText) view.findViewById(R.id.lastName);
        email     = (EditText) view.findViewById(R.id.email);
        password1 = (EditText) view.findViewById(R.id.password1);
        password2 = (EditText) view.findViewById(R.id.password2);

        ok      = (Button) view.findViewById(R.id.register);
        cancel  = (Button) view.findViewById(R.id.cancel);

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

                if (user == null) Toast.makeText(getActivity(), "Something went wrong :(", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getActivity(), UserConfigurationActivity.class);
                    intent.putExtra(UserConfigurationActivity.USER, user);
                    startActivity(intent);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    AsyncTask<RegistrationForm, Void, User> registration = new AsyncTask<RegistrationForm, Void, User>() {
        @Override
        protected User doInBackground(RegistrationForm... forms) {
            return AccountManager.createNewUser(forms[0]);
        }
    };
}
