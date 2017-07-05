package ru.innopolis.justchat.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import ru.innopolis.justchat.R;
import ru.innopolis.justchat.model.User;

public class ConfigurationActivity extends BaseActivity {
    public static final String USER = "user";

    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        User user = getIntent().getParcelableExtra(USER);

        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText("Wellcome to Just Chat App " + user.getFirstName() + "!");
    }
}
