package ru.innopolis.justchat.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import ru.innopolis.justchat.R;
import ru.innopolis.justchat.model.User;
import ru.innopolis.justchat.view.fragment.ChatFragment;
import ru.innopolis.justchat.view.fragment.UserConfigurationFragment;

public class ChatActivity extends Activity {

    public static final String USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        User user = getIntent().getParcelableExtra(USER);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ChatFragment.USER, user);

        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.chatFragment, fragment);
        transaction.commit();
    }
}
