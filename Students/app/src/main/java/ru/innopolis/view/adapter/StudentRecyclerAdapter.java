package ru.innopolis.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.innopolis.model.Group;
import ru.innopolis.model.Student;
import ru.innopolis.view.R;
import ru.innopolis.view.StudentDetailActivity;

/**
 * Created by ibrahim on 6/23/2017.
 */

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder> {

    private Group group;
    private List<Student> students;

    public StudentRecyclerAdapter(Group group) {
        this.group = group;
        this.students = group.getStudents();
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.setData(student, position);
        holder.listen();
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView studentName;
        private int position;
        private Student student;

        public StudentViewHolder(View itemView) {
            super(itemView);
            studentName = (TextView) itemView.findViewById(R.id.txvStudentName);
        }

        public void setData(Student student, int position) {
            this.studentName.setText(student.getFirstName() + " " + student.getLastName());
            this.position = position;
            this.student = student;
        }

        public void listen() {
            studentName.setOnClickListener(StudentViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), StudentDetailActivity.class);
            intent.putExtra(StudentDetailActivity.STUDENT, student);
            intent.putExtra(StudentDetailActivity.GROUP, group);
            v.getContext().startActivity(intent);
        }
    }
}
