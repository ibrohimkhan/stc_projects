package ru.innopolis.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.innopolis.model.Group;
import ru.innopolis.view.adapter.StudentRecyclerAdapter;
import ru.innopolis.view.fragment.StudentListInGroupFragment;

public class StudentListActivity extends Activity {

    public static final String GROUP = "group";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        Group group = (Group) getIntent().getParcelableExtra(GROUP);
        Bundle bundle = new Bundle();
        bundle.putParcelable(StudentListInGroupFragment.GROUP, group);

        StudentListInGroupFragment fragment = new StudentListInGroupFragment();
        fragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.studentListActivity, fragment);
        transaction.commit();
    }
}
