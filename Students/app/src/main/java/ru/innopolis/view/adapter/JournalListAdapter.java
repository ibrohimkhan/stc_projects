package ru.innopolis.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.innopolis.model.Contact;
import ru.innopolis.model.Lesson;
import ru.innopolis.model.Student;
import ru.innopolis.view.R;

/**
 * Created by ibrahim on 6/25/2017.
 */

public class JournalListAdapter extends BaseAdapter {
    private Context context;
    private List<Lesson> lessons;
    private Student student;

    public JournalListAdapter(Context context, List<Lesson> lessons, Student student) {
        this.context = context;
        this.lessons = lessons;
        this.student = student;
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public Object getItem(int position) {
        return lessons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.journal_list_view_row_items, parent, false);
        }

        Lesson lesson = (Lesson) getItem(position);
        Boolean isChecked = lesson.getJournal().getListeners().get(student);

        TextView txvLessonSubject = (TextView) convertView.findViewById(R.id.txvLessonSubject);
        txvLessonSubject.setText(lesson.getSubject());

        ImageView img = (ImageView) convertView.findViewById(R.id.imgVisitIcon);

        if (isChecked) img.setImageResource(R.drawable.ic_check);
        else img.setImageResource(R.drawable.ic_close);

        return convertView;
    }
}
