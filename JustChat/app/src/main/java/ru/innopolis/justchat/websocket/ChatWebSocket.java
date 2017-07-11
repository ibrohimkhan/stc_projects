package ru.innopolis.justchat.websocket;

import android.os.Handler;
import android.os.Message;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by ibrahim on 7/11/2017.
 */

public class ChatWebSocket {

    public enum ConnectionStatus {
        CONNECTED,
        DISCONNECTED
    }

    public interface ServerListener {
        void onNewMessage(String message);
        void onStatusChange(ConnectionStatus status);
    }

    private WebSocket webSocket;
    private OkHttpClient httpClient;
    private String serverUrl;
    private Handler messageHandler;
    private Handler statusHandler;
    private ServerListener listener;

    public ChatWebSocket(String serverUrl) {
        httpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        this.serverUrl = serverUrl;
    }

    private class SocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            Message message = statusHandler.obtainMessage(0, ConnectionStatus.CONNECTED);
            statusHandler.sendMessage(message);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            Message message = statusHandler.obtainMessage(0, ConnectionStatus.DISCONNECTED);
            statusHandler.sendMessage(message);
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Message message = messageHandler.obtainMessage(0, text);
            messageHandler.sendMessage(message);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            disconnect();
        }
    }

    public void connect(ServerListener _listener) {
        Request request = new Request.Builder()
                .url(serverUrl)
                .build();

        webSocket = httpClient.newWebSocket(request, new SocketListener());
        this.listener = _listener;

        messageHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                listener.onNewMessage((String) message.obj);
                return true;
            }
        });

        statusHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                listener.onStatusChange((ConnectionStatus) message.obj);
                return true;
            }
        });
    }

    public void sendMessage(String message) {
        webSocket.send(message);
    }

    public void disconnect() {
        webSocket.cancel();
        listener = null;

        messageHandler.removeCallbacksAndMessages(null);
        statusHandler.removeCallbacksAndMessages(null);
    }
}
