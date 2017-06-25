package ru.innopolis.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.innopolis.model.Group;
import ru.innopolis.view.adapter.StudentRecyclerAdapter;

public class StudentListActivity extends Activity {

    public static final String GROUP = "group";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        Group group = (Group) getIntent().getParcelableExtra(GROUP);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);
        StudentRecyclerAdapter studentRecyclerAdapter = new StudentRecyclerAdapter(group, this);
        recyclerView.setAdapter(studentRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
