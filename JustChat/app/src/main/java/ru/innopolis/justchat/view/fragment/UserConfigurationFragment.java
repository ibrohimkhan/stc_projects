package ru.innopolis.justchat.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.innopolis.justchat.R;
import ru.innopolis.justchat.model.User;

/**
 * Created by ibrahim on 7/7/2017.
 */

public class UserConfigurationFragment extends Fragment {
    public static final String USER = "user";
    private TextView welcome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_configuration_fragment, container, false);

        User user = (User) getArguments().get(USER);

        welcome = (TextView) view.findViewById(R.id.welcome);
        welcome.setText("Wellcome to Just Chat App " + user.getFirstName() + "!");

        return view;
    }
}
