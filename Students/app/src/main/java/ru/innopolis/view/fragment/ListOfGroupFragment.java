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
import java.util.List;

import ru.innopolis.model.Group;
import ru.innopolis.utils.FakeDataGenerator;
import ru.innopolis.view.R;
import ru.innopolis.view.adapter.GroupRecyclerAdapter;

/**
 * Created by ibrahim on 6/28/2017.
 */

public class ListOfGroupFragment extends Fragment {

    private EditText searchGroup;
    private GroupRecyclerAdapter groupRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        searchGroup = (EditText) view.findViewById(R.id.searchGroup);

        final List<Group> groups = FakeDataGenerator.createGroup();

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.groupRecyclerView);
        groupRecyclerAdapter = new GroupRecyclerAdapter(groups);
        recyclerView.setAdapter(groupRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                groupRecyclerAdapter = new GroupRecyclerAdapter(filtered);
                recyclerView.setAdapter(groupRecyclerAdapter);

                groupRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }
}
