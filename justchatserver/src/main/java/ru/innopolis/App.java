package ru.innopolis;

import com.google.gson.Gson;
import ru.innopolis.entity.Account;
import ru.innopolis.entity.RegistrationForm;
import ru.innopolis.entity.User;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.UserService;

import static spark.Spark.*;

/**
 * Created by ibrahim on 7/5/2017.
 */
public class App {
    public static void main(String[] args) {

        port(4567);

        post("/login", (request, response) -> {
            Gson gson = new Gson();
            String body = request.body();

            Account account = gson.fromJson(body, Account.class);
            User user = UserService.findUserByAccount(account);

            response.type("application/json");
            String result = null;

            if (user != null) {
                response.status(200);
                result = gson.toJson(user);
            } else {
                response.status(500);
                result = "Something went wrong!";
            }

            return result;
        });

        post("/register", (request, response) -> {
            Gson gson = new Gson();
            String body = request.body();
            RegistrationForm form = gson.fromJson(body, RegistrationForm.class);

            Account account = new Account(form.getEmail(), form.getPassword());
            Long accountId = AccountService.createNewAccount(account);
            account.setId(accountId);

            User user = new User(form.getFirstName(), form.getLastName(), account);
            Long userId = UserService.createNewUser(user);
            user.setId(userId);

            response.status(200);
            response.type("application/json");
            String result = gson.toJson(user);

            return result;
        });

        post("/updateuserinfo", ((request, response) -> {
            Gson gson = new Gson();
            String body = request.body();

            User user = gson.fromJson(body, User.class);
            UserService.updateUser(user);

            response.status(200);
            response.type("application/json");

            return response;
        }));

        webSocket("/chat", null);
        init();
    }
}
