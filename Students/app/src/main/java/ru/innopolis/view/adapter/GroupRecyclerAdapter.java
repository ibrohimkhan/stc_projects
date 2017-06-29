package ru.innopolis.view.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.innopolis.model.Group;
import ru.innopolis.view.R;
import ru.innopolis.view.StudentListActivity;

/**
 * Created by ibrahim on 6/22/2017.
 */
public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.GroupViewHolder> {

    public interface DataListener {
        void listen(Group group);
    }

    private List<Group> groups;
    private DataListener listener;

    public GroupRecyclerAdapter(List<Group> groups, DataListener listener) {
        this.groups = groups;
        this.listener = listener;
    }

    public GroupRecyclerAdapter() {
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        Group group = groups.get(position);
        holder.setData(group, position);
    }

    class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
        private TextView groupName;
        private int position;
        private Group group;

        public Group getGroup() {
            return group;
        }

        public GroupViewHolder(View itemView) {
            super(itemView);
            groupName = (TextView) itemView.findViewById(R.id.txvGroupName);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void setData(Group group, int position) {
            this.groupName.setText(group.getGroupName());
            this.position = position;
            this.group = group;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), StudentListActivity.class);
            intent.putExtra(StudentListActivity.GROUP, group);
            v.getContext().startActivity(intent);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            if (listener != null) listener.listen(group);
            menu.add(0, R.id.sms, 0, "Send SMS");
        }
    }
}