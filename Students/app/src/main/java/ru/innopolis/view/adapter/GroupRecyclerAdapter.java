package ru.innopolis.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.innopolis.model.Group;
import ru.innopolis.view.R;
import ru.innopolis.view.StudentListActivity;

/**
 * Created by ibrahim on 6/22/2017.
 */

public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.GroupViewHolder> {

    private List<Group> groups;
    private LayoutInflater inflater;
    private Context context;

    public GroupRecyclerAdapter(Context context, List<Group> groups) {
        this.context = context;
        this.groups = groups;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.group_list_item, parent, false);
        GroupViewHolder holder = new GroupViewHolder(view);

        return holder;
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        Group group = groups.get(position);
        holder.setData(group, position);
        holder.listen();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView groupName;
        private int position;
        private Group group;

        public GroupViewHolder(View itemView) {
            super(itemView);
            groupName = (TextView) itemView.findViewById(R.id.txvGroupName);
        }

        public void setData(Group group, int position) {
            this.groupName.setText(group.getGroupName());
            this.position = position;
            this.group = group;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, StudentListActivity.class);
            intent.putExtra(StudentListActivity.GROUP, group);
            context.startActivity(intent);
        }

        public void listen() {
            groupName.setOnClickListener(GroupViewHolder.this);
        }
    }

}
