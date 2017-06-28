package ru.innopolis.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.innopolis.model.Group;
import ru.innopolis.view.R;
import ru.innopolis.view.adapter.StudentRecyclerAdapter;

/**
 * Created by ibrahim on 6/29/2017.
 */

public class StudentListInGroupFragment extends Fragment {
    public static final String GROUP = "group";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);

        Bundle bundle = this.getArguments();
        Group group = (Group) bundle.get(GROUP);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.studentRecyclerView);
        StudentRecyclerAdapter studentRecyclerAdapter = new StudentRecyclerAdapter(group);
        recyclerView.setAdapter(studentRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
