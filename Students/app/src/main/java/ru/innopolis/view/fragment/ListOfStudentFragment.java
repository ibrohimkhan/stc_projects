package ru.innopolis.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import ru.innopolis.model.Group;
import ru.innopolis.model.Student;
import ru.innopolis.view.R;
import ru.innopolis.view.StudentDetailActivity;

/**
 * Created by ibrahim on 6/29/2017.
 */

public class ListOfStudentFragment extends Fragment {

    public static final String ALL_STUDENTS = "all_students";
    public static final String ALL_GROUPS = "all_groups";

    private ListView listOfAllStudent;
    private EditText searchStudents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_all_student, container, false);

        Bundle bundle = this.getArguments();
        final List<Student> students = bundle.getParcelableArrayList(ALL_STUDENTS);
        final List<Group> groups = bundle.getParcelableArrayList(ALL_GROUPS);

        final ArrayAdapter<Student> studentArrayAdapter = new ArrayAdapter<Student>(getActivity(), android.R.layout.simple_list_item_1, students);
        listOfAllStudent = (ListView) view.findViewById(R.id.listOfAllStudents);
        listOfAllStudent.setAdapter(studentArrayAdapter);
        listOfAllStudent.setTextFilterEnabled(true);

        listOfAllStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = students.get(position);
                Group group = findStudentGroup(student);

                Intent intent = new Intent(getActivity(), StudentDetailActivity.class);
                intent.putExtra(StudentDetailActivity.STUDENT, student);
                intent.putExtra(StudentDetailActivity.GROUP, group);
                startActivity(intent);
            }

            private Group findStudentGroup(Student student) {
                for (Group group : groups) {
                    for (Student std : group.getStudents()) {
                        if (std.equals(student)) return group;
                    }
                }

                return null;
            }
        });

        searchStudents = (EditText) view.findViewById(R.id.searchStudents);
        searchStudents.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                studentArrayAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }
}
