package ru.innopolis.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Date;

import ru.innopolis.manager.GroupManager;
import ru.innopolis.manager.LessonManager;
import ru.innopolis.model.Group;
import ru.innopolis.model.Lesson;
import ru.innopolis.utils.DateUtils;

public class EditLessonActivity extends Activity {

    public static final String LESSON = "lesson";

    private TextView dateTxv;
    private Button setDateBtn;

    private TextView timeTxv;
    private Button setTimeBtn;

    private EditText subjectEtx;
    private EditText descriptionEtx;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lesson);

        final Lesson lesson = getIntent().getParcelableExtra(LESSON);

        dateTxv = (TextView) findViewById(R.id.dateTxv);
        setDateBtn = (Button) findViewById(R.id.setDateBtn);

        timeTxv = (TextView) findViewById(R.id.timeTxv);
        setTimeBtn = (Button) findViewById(R.id.setTimeBtn);

        subjectEtx = (EditText) findViewById(R.id.subjectEtx);
        descriptionEtx = (EditText) findViewById(R.id.descriptionEtx);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        dateTxv.setText(parseDate(lesson.getDate()));
        timeTxv.setText(parseTime(lesson.getDate()));
        subjectEtx.setText(lesson.getSubject());

        if (lesson.getDescription() != null) descriptionEtx.setText(lesson.getDescription());

        final Date date = lesson.getDate();
        final Date newDate = new Date();

        setDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        newDate.setYear(i);
                        newDate.setMonth(i1);
                        newDate.setDate(i2);
                        dateTxv.setText(DateUtils.formatDate(newDate));
                    }
                };

                DatePickerDialog datePicker = new DatePickerDialog(EditLessonActivity.this, dateSetListener, date.getYear(), date.getMonth(), date.getDay());
                datePicker.show();
            }
        });

        setTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        newDate.setHours(i);
                        newDate.setMinutes(i1);
                        timeTxv.setText(DateUtils.formatTime(newDate));
                    }
                };

                TimePickerDialog timePicker = new TimePickerDialog(EditLessonActivity.this, timeSetListener, date.getHours(), date.getMinutes(), false);
                timePicker.show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditLessonActivity.this);
                builder.setMessage("All your changes will be saved!");
                builder.setTitle("Edit Lesson");

                ArrayList<Group> groups = (ArrayList<Group>) GroupManager.getAllGroups();
                final Intent intent = new Intent(EditLessonActivity.this, ListofLessonsActivity.class);
                intent.putParcelableArrayListExtra(ListofLessonsActivity.ALL_GROUPS, groups);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        lesson.setDate(newDate);
                        lesson.setSubject(subjectEtx.getText().toString());
                        lesson.setDescription(descriptionEtx.getText().toString());

                        LessonManager.updateLesson(lesson);
                        startActivity(intent);
                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(intent);
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private String parseTime(Date date) {
        String formated = DateUtils.formatDateToString(date).split(" ")[1];
        return formated;
    }

    private String parseDate(Date date) {
        String formated = DateUtils.formatDateToString(date).split(" ")[0];
        return formated;
    }
}
