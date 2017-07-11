package ru.innopolis.justchat.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.innopolis.justchat.R;
import ru.innopolis.justchat.model.User;
import ru.innopolis.justchat.view.adapter.MessagesRecyclerViewAdapter;
import ru.innopolis.justchat.websocket.ChatWebSocket;

/**
 * Created by ibrahim on 7/10/2017.
 */

public class ChatFragment extends Fragment implements ChatWebSocket.ServerListener {

    public static final String USER = "user";
    private static final String SERVER_URL = "ws://10.240.20.119:4567/chat";

    private MessagesRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private EditText editText;
    private Button button;

    private User user;
    private ChatWebSocket chatWebSocket;

    private List<String> msgData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_fragment, container, false);

        user = (User) getArguments().get(USER);

        adapter = new MessagesRecyclerViewAdapter(msgData);
        recyclerView = (RecyclerView) view.findViewById(R.id.listOfMessages);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        editText = (EditText) view.findViewById(R.id.message);

        button = (Button) view.findViewById(R.id.send);
        button.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();
                if (msg == null || msg.isEmpty()) {
                    Toast.makeText(getActivity(), "There is no data to send!", Toast.LENGTH_LONG).show();
                    return;
                }

                updateAdapterMessageList(msg);
                chatWebSocket.sendMessage(msg);
            }
        });

        chatWebSocket = new ChatWebSocket(SERVER_URL);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        chatWebSocket.connect(this, user);
    }

    @Override
    public void onPause() {
        super.onPause();
        chatWebSocket.disconnect();
    }

    @Override
    public void onNewMessage(String message) {
        updateAdapterMessageList(message);
    }

    @Override
    public void onStatusChange(ChatWebSocket.ConnectionStatus status) {
        String currentStatus = (status == ChatWebSocket.ConnectionStatus.CONNECTED) ? "CONNECTED" : "DISCONNECTED";
        button.setEnabled(status == ChatWebSocket.ConnectionStatus.CONNECTED);
    }

    private void updateAdapterMessageList(String message) {
        msgData.add(message);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MessagesRecyclerViewAdapter(msgData);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
