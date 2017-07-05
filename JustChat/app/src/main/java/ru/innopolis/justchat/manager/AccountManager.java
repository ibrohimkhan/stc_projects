package ru.innopolis.justchat.manager;

import java.util.IllegalFormatException;

import ru.innopolis.justchat.model.Account;
import ru.innopolis.justchat.model.RegistrationForm;
import ru.innopolis.justchat.model.User;

/**
 * Created by ibrahim on 7/5/2017.
 */

public class AccountManager {
    public static User authenticate(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException("Error in user credentials!");

        Account account = new Account(email, password);
        return NetworkManager.authenticate(account);
    }

    public static User createNewUser(RegistrationForm form) {
        if (form == null) throw new RuntimeException();
        return NetworkManager.createNewUser(form);
    }
}
