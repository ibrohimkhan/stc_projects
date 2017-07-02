package ru.innopolis.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ru.innopolis.model.Journal;
import ru.innopolis.model.Lesson;
import ru.innopolis.model.Student;
import ru.innopolis.utils.DateUtils;
import ru.innopolis.view.R;

/**
 * Created by ibrahim on 7/1/2017.
 */

public class JournalRecyclerAdapter extends RecyclerView.Adapter<JournalRecyclerAdapter.JournalViewHolder> {

    private Map<Lesson, Journal> journals;

    public JournalRecyclerAdapter(Map<Lesson, Journal> journals) {
        this.journals = journals;
    }

    @Override
    public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_list_item, parent, false);
        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JournalViewHolder holder, int position) {
        List<Journal> listOfJournal = new ArrayList<>(journals.values());
        List<Lesson> listOfLessons = new ArrayList<>(journals.keySet());

        Journal journal = listOfJournal.get(position);
        Lesson lesson = listOfLessons.get(position);

        holder.bind(lesson, journal);
    }

    @Override
    public int getItemCount() {
        return journals.size();
    }

    public class JournalViewHolder extends RecyclerView.ViewHolder {

        private TextView dateOfRegister;
        private TextView lessonSubject;
        private TextView numberOfVisiters;

        public JournalViewHolder(View itemView) {
            super(itemView);

            dateOfRegister = (TextView) itemView.findViewById(R.id.dateOfRegister);
            lessonSubject = (TextView) itemView.findViewById(R.id.lessonSubject);
            numberOfVisiters = (TextView) itemView.findViewById(R.id.numberOfVisiters);
        }

        public void bind(Lesson lesson, Journal journal) {
            String time = DateUtils.formatDateToString(journal.getDate());

            dateOfRegister.setText(time);
            lessonSubject.setText(lesson.getSubject());
            numberOfVisiters.setText("Visited: " + countVisiters(journal.getListeners()));
        }

        private int countVisiters(Map<Student, Boolean> listeners) {
            int counter = 0;
            for (boolean bool : listeners.values()) if (bool) counter++;

            return counter;
        }
    }
}
