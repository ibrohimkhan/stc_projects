package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.innopolis.utils.FakeDataGenerator;
import ru.innopolis.view.adapter.GroupRecyclerAdapter;

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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.groupRecyclerView);
        GroupRecyclerAdapter groupRecyclerAdapter = new GroupRecyclerAdapter(GroupActivity.this, FakeDataGenerator.createGroup());
        recyclerView.setAdapter(groupRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
