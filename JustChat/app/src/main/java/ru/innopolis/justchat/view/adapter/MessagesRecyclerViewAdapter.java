package ru.innopolis.justchat.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.innopolis.justchat.R;

/**
 * Created by ibrahim on 7/10/2017.
 */

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder> {

    private List<String> messages;

    public MessagesRecyclerViewAdapter(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txvMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            txvMessage = (TextView) itemView.findViewById(R.id.messageText);
        }

        public void bind(String message) {
            txvMessage.setText(message);
        }
    }
}
