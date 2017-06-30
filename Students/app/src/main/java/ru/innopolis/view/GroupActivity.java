package ru.innopolis.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.innopolis.manager.GroupManager;
import ru.innopolis.model.Group;
import ru.innopolis.view.fragment.ListOfGroupFragment;

/**
 * Created by ibrahim on 6/22/2017.
 */

public class GroupActivity extends Activity {
    public static final String USERNAME = "username";
    private ArrayList<Group> groups = (ArrayList<Group>) GroupManager.getAllGroups();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        String username = (String) getIntent().getExtras().getCharSequence(USERNAME);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ListOfGroupFragment.ALL_GROUPS, groups);

        ListOfGroupFragment fragment = new ListOfGroupFragment();
        fragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activityGroup, fragment);
        transaction.commit();
    }
}
