package ru.innopolis.view.fragment;

import android.app.Fragment;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ru.innopolis.model.Group;
import ru.innopolis.model.Journal;
import ru.innopolis.model.Lesson;
import ru.innopolis.view.R;
import ru.innopolis.view.adapter.GroupRecyclerAdapter;
import ru.innopolis.view.adapter.JournalRecyclerAdapter;

/**
 * Created by ibrahim on 7/1/2017.
 */

public class ListOfAllJournalsFragment extends Fragment {

    public static final String ALL_GROUPS = "all_groups";

    private EditText searchJournal;
    private RecyclerView recyclerView;
    private JournalRecyclerAdapter journalRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_all_journals, container, false);

        final List<Group> groups = getArguments().getParcelableArrayList(ALL_GROUPS);
        Map<Lesson, Journal> journals = new LinkedHashMap<>();

        for (Group group : groups) {
            List<Lesson> lessons = group.getLessons();
            for (Lesson lesson : lessons) journals.put(lesson, lesson.getJournal());
        }

        searchJournal = (EditText) view.findViewById(R.id.searchJournal);
        recyclerView = (RecyclerView) view.findViewById(R.id.listOfAllJournals);

        journalRecyclerAdapter = new JournalRecyclerAdapter(journals);
        recyclerView.setAdapter(journalRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        searchJournal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Map<Lesson, Journal> filtered = new LinkedHashMap<>();

                for (Group group : groups) {
                    if (group.getGroupName().contains(s)) {
                        List<Lesson> lessons = group.getLessons();
                        for (Lesson lesson : lessons) filtered.put(lesson, lesson.getJournal());
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                journalRecyclerAdapter = new JournalRecyclerAdapter(filtered);
                recyclerView.setAdapter(journalRecyclerAdapter);

                journalRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }
}
