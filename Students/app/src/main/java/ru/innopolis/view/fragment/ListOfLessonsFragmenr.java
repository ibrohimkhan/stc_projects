package ru.innopolis.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ru.innopolis.model.Group;
import ru.innopolis.model.Lesson;
import ru.innopolis.view.EditLessonActivity;
import ru.innopolis.view.R;
import ru.innopolis.view.adapter.LessonRecyclerAdapter;

/**
 * Created by ibrahim on 7/2/2017.
 */

public class ListOfLessonsFragmenr extends Fragment implements LessonRecyclerAdapter.EditLessonListener {

    public static final String ALL_GROUPS = "all_groups";
    private EditText search;
    private RecyclerView recyclerView;
    private LessonRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_lessons, container, false);

        final List<Group> groups = getArguments().getParcelableArrayList(ALL_GROUPS);

        List<Lesson> lessons = new ArrayList<>();
        for (Group group : groups) lessons.addAll(group.getLessons());

        search = (EditText) view.findViewById(R.id.searchLessons);
        recyclerView = (RecyclerView) view.findViewById(R.id.lessonsRecyclerView);

        adapter = new LessonRecyclerAdapter(lessons, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Lesson> filtered = new ArrayList<Lesson>();

                for (Group group : groups) {
                    if (group.getGroupName().contains(s)) {
                        filtered.addAll(group.getLessons());
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                adapter = new LessonRecyclerAdapter(filtered, ListOfLessonsFragmenr.this);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return view;
    }

    @Override
    public void onEdit(Lesson lesson) {
        Intent intent = new Intent(getActivity(), EditLessonActivity.class);
        intent.putExtra(EditLessonActivity.LESSON, lesson);
        startActivity(intent);
    }
}
