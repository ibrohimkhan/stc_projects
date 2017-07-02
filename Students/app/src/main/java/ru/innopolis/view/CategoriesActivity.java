package ru.innopolis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ru.innopolis.manager.GroupManager;
import ru.innopolis.model.Group;
import ru.innopolis.model.Student;


public class CategoriesActivity extends Activity {

    public static final String USERNAME = "username";
    public static final String ALL_STUDENTS = "all_students";

    private String[] categoryItems = "Groups Students Journals Lessons".split(" ");
    private ListView categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final String username = getIntent().getStringExtra(USERNAME);
        final ArrayList<Student> students = getIntent().getParcelableArrayListExtra(ALL_STUDENTS);
        final ArrayList<Group> groups = (ArrayList<Group>) GroupManager.getAllGroups();

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
                        intent.putParcelableArrayListExtra(ListOfAllStudentActivity.ALL_GROUPS, groups);

                        break;

                    case "Journals":
                        intent = new Intent(CategoriesActivity.this, ListOfAllJournalsActivity.class);
                        intent.putParcelableArrayListExtra(ListOfAllJournalsActivity.ALL_GROUPS, groups);

                        break;

                    case "Lessons":

                        break;

                }

                startActivity(intent);
            }
        });
    }
}
