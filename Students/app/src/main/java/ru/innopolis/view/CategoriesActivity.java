package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.innopolis.model.Student;
import ru.innopolis.utils.FakeDataGenerator;


public class CategoriesActivity extends Activity {

    public static final String USERNAME = "username";
    public static final String ALL_STUDENTS = "all_students";

    private ListView categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final String username = getIntent().getStringExtra(USERNAME);
        final ArrayList<Student> students = getIntent().getParcelableArrayListExtra(ALL_STUDENTS);

        final String[] categoryItems = "Groups Students".split(" ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryItems);

        categories = (ListView) findViewById(R.id.categoriesList);
        categories.setAdapter(adapter);
        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClicked = categoryItems[position];
                Intent intent = null;

                switch (itemClicked) {
                    case "Groups":
                        intent = new Intent(CategoriesActivity.this, GroupActivity.class);
                        intent.putExtra(GroupActivity.USERNAME, username);

                        break;

                    case "Students":
                        intent = new Intent(CategoriesActivity.this, ListOfAllStudentActivity.class);
                        intent.putParcelableArrayListExtra(ListOfAllStudentActivity.ALL_STUDENTS, students);
                        intent.putParcelableArrayListExtra(ListOfAllStudentActivity.ALL_GROUPS, (ArrayList<? extends Parcelable>) FakeDataGenerator.createGroup());

                        break;

                }

                startActivity(intent);
            }
        });
    }
}
