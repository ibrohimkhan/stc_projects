package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ru.innopolis.model.Group;
import ru.innopolis.utils.FakeDataGenerator;
import ru.innopolis.view.adapter.GroupRecyclerAdapter;

/**
 * Created by ibrahim on 6/22/2017.
 */

public class GroupActivity extends Activity {

    public static final String USERNAME = "username";
    private EditText searchGroup;
    private GroupRecyclerAdapter groupRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        searchGroup = (EditText) findViewById(R.id.searchGroup);
        String username = (String) getIntent().getExtras().getCharSequence(USERNAME);

        final List<Group> groups = FakeDataGenerator.createGroup();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.groupRecyclerView);
        groupRecyclerAdapter = new GroupRecyclerAdapter(GroupActivity.this, groups);
        recyclerView.setAdapter(groupRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        searchGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<Group> filtered = new ArrayList<Group>();

                for (Group group : groups) {
                    if (group.getGroupName().contains(s)) filtered.add(group);
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(GroupActivity.this));
                groupRecyclerAdapter = new GroupRecyclerAdapter(GroupActivity.this, filtered);
                recyclerView.setAdapter(groupRecyclerAdapter);

                groupRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
