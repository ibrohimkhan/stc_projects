package ru.innopolis.justchat.manager;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ibrahim on 7/4/2017.
 */

public class NetworkManager {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String IP_ADDRESS = "10.240.19.71:4567";

    public static String authenticate(String user, String pass) {
        String responce = null;

        try {
            Gson gson = new Gson();
            String json = gson.toJson(user + " " + pass);
            responce = post(IP_ADDRESS + "/login", json);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responce;
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
