package ru.innopolis.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ru.innopolis.model.Lesson;
import ru.innopolis.utils.DateUtils;
import ru.innopolis.view.R;

/**
 * Created by ibrahim on 7/2/2017.
 */

public class LessonRecyclerAdapter extends RecyclerView.Adapter<LessonRecyclerAdapter.LessonViewHolder> {

    public interface EditLessonListener {
        void onEdit(Lesson lesson);
    }

    private List<Lesson> lessons;
    private EditLessonListener listener;

    public LessonRecyclerAdapter(List<Lesson> lessons, EditLessonListener listener) {
        this.lessons = lessons;
        this.listener = listener;
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_list_item, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.bind(lesson);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTxv;
        private TextView subjectTxv;
        private TextView lectorTxv;
        private Button edit;

        public LessonViewHolder(View itemView) {
            super(itemView);

            dateTxv     = (TextView) itemView.findViewById(R.id.txvDate);
            subjectTxv  = (TextView) itemView.findViewById(R.id.lessonSubjectTxv);
            lectorTxv   = (TextView) itemView.findViewById(R.id.lectorTxv);
            edit        = (Button) itemView.findViewById(R.id.editLessonBtn);
        }

        public void bind(final Lesson lesson) {
            String time = DateUtils.formatDateToString(lesson.getDate());
            dateTxv.setText(time);

            subjectTxv.setText(lesson.getSubject());
            lectorTxv.setText(lesson.getLector().getFirstName() + " " + lesson.getLector().getLastName());

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEdit(lesson);
                }
            });
        }
    }
}
