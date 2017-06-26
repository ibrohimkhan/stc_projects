package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ru.innopolis.model.Group;
import ru.innopolis.model.Student;
import ru.innopolis.utils.FakeDataGenerator;

public class ListOfAllStudentActivity extends Activity {
    public static final String ALL_STUDENTS = "all_students";
    public static final String ALL_GROUPS = "all_groups";

    private ListView listOfAllStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_all_student);

        final List<Student> students = getIntent().getParcelableArrayListExtra(ALL_STUDENTS);
        final List<Group> groups = getIntent().getParcelableArrayListExtra(ALL_GROUPS);

        ArrayAdapter<Student> studentArrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        listOfAllStudent = (ListView) findViewById(R.id.listOfAllStudents);
        listOfAllStudent.setAdapter(studentArrayAdapter);

        listOfAllStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = students.get(position);
                Group group = findStudentGroup(student);

                Intent intent = new Intent(ListOfAllStudentActivity.this, StudentDetailActivity.class);
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
    }
}
