package ru.innopolis.websocket;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import ru.innopolis.entity.User;

import java.io.IOException;

/**
 * Created by ibrahim on 7/11/2017.
 */
@WebSocket
public class WebsocketHandler {

    @OnWebSocketConnect
    public void connected(Session session) {
        String json = session.getUpgradeRequest().getHeader("user");
        User user = new Gson().fromJson(json, User.class);
        user.setSession(session);
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        session.getRemote().sendString(message); // and send it back
    }

}
