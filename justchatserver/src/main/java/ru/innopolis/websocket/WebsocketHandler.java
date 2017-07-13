package ru.innopolis.websocket;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import ru.innopolis.entity.State;
import ru.innopolis.entity.User;
import ru.innopolis.logic.UserPairing;
import ru.innopolis.service.UserService;

import java.io.IOException;

/**
 * Created by ibrahim on 7/11/2017.
 */
@WebSocket
public class WebsocketHandler {

    private static UserPairing pair = UserPairing.getInstance();

    @OnWebSocketConnect
    public void connected(Session session) {
        String json = session.getUpgradeRequest().getHeader("user");
        User user = new Gson().fromJson(json, User.class);
        user.setSession(session);

        pair.link(user);
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        User user = pair.findUserBySession(session);
        boolean unlinked = pair.unlink(session);

        if (unlinked) {
            user.setSession(null);
            user.setState(State.OFFLINE);
            user.setType(null);
            user.setLanguage(null);

            UserService.updateUser(user);
        }
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        User[] couple = pair.findCouple(session);

        if (couple[0] == null || couple[1] == null) {
            session.getRemote().sendString("Could not find you a pair, sorry! Please, try again later.");
            return;
        }

        User sender = null;
        User reciever = null;

        if (couple[0].getSession().equals(session)) {
            sender = couple[0];
            reciever = couple[1];
        } else {
            reciever = couple[0];
            sender = couple[1];
        }

        sender.setState(State.ACTIVE);
        reciever.setState(State.ACTIVE);

        reciever.getSession().getRemote().sendString(sender.getFirstName() + ":\n" + message); // and send it back
    }

}
