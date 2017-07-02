package ru.innopolis.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;

import ru.innopolis.model.Group;
import ru.innopolis.view.fragment.ListOfLessonsFragmenr;

public class ListofLessonsActivity extends Activity {

    public static final String ALL_GROUPS = "all_groups";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_lessons);

        ArrayList<Group> groups = getIntent().getParcelableArrayListExtra(ALL_GROUPS);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ListOfLessonsFragmenr.ALL_GROUPS, groups);

        ListOfLessonsFragmenr fragmenr = new ListOfLessonsFragmenr();
        fragmenr.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.listOfLessonsActivity, fragmenr);
        transaction.commit();
    }
}
