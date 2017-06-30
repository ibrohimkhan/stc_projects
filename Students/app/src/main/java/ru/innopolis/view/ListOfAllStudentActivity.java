package ru.innopolis.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;

import ru.innopolis.model.Group;
import ru.innopolis.model.Student;
import ru.innopolis.view.fragment.ListOfStudentFragment;

public class ListOfAllStudentActivity extends Activity {

    public static final String ALL_STUDENTS = "all_students";
    public static final String ALL_GROUPS = "all_groups";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_all_student);

        ArrayList<Student> students = getIntent().getParcelableArrayListExtra(ALL_STUDENTS);
        ArrayList<Group> groups = getIntent().getParcelableArrayListExtra(ALL_GROUPS);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ListOfStudentFragment.ALL_STUDENTS, students);
        bundle.putParcelableArrayList(ListOfStudentFragment.ALL_GROUPS, groups);

        ListOfStudentFragment fragment = new ListOfStudentFragment();
        fragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.listOfAllStudentsActivity, fragment);
        transaction.commit();
    }
}
