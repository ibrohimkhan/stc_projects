package ru.innopolis.justchat.manager;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.innopolis.justchat.model.Account;
import ru.innopolis.justchat.model.RegistrationForm;
import ru.innopolis.justchat.model.User;

/**
 * Created by ibrahim on 7/4/2017.
 */

public class NetworkManager {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String IP_ADDRESS = "http://10.240.19.71:4567";

    public static User authenticate(Account account) {
        User user = null;

        try {
            Gson gson = new Gson();
            String json = gson.toJson(account);
            String responce = post(IP_ADDRESS + "/login", json);

            user = gson.fromJson(responce, User.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static User createNewUser(RegistrationForm form) {
        User user = null;

        try {
            Gson gson = new Gson();
            String json = gson.toJson(form);
            String responce = post(IP_ADDRESS + "/register", json);

            user = gson.fromJson(responce, User.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void connection(User user) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(user);
            String responce = post(IP_ADDRESS + "/connect", json);

            // something to do here

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String post(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
