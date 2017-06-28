package ru.innopolis.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.innopolis.view.fragment.ListOfGroupFragment;

/**
 * Created by ibrahim on 6/22/2017.
 */

public class GroupActivity extends Activity {

    public static final String USERNAME = "username";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        String username = (String) getIntent().getExtras().getCharSequence(USERNAME);

        ListOfGroupFragment fragment = new ListOfGroupFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activityGroup, fragment);
        transaction.commit();
    }
}
