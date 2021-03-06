package ru.innopolis.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;

import ru.innopolis.model.Group;
import ru.innopolis.view.component.BaseActivity;
import ru.innopolis.view.fragment.ListOfAllJournalsFragment;

public class ListOfAllJournalsActivity extends BaseActivity {

    public static final String ALL_GROUPS = "all_groups";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_all_journals);

        ArrayList<Group> groups = getIntent().getParcelableArrayListExtra(ALL_GROUPS);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ListOfAllJournalsFragment.ALL_GROUPS, groups);

        ListOfAllJournalsFragment fragment = new ListOfAllJournalsFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.listOfAllJournalsActivity, fragment);
        transaction.commit();
    }
}
