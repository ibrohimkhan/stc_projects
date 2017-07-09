package ru.innopolis.justchat.manager;

import ru.innopolis.justchat.model.Account;
import ru.innopolis.justchat.model.RegistrationForm;
import ru.innopolis.justchat.model.User;

/**
 * Created by ibrahim on 7/5/2017.
 */

public class AccountManager {
    public static User authenticate(String email, String password) {
        Account account = new Account(email, password);
        return NetworkManager.authenticate(account);
    }

    public static User createNewUser(RegistrationForm form) {
        return NetworkManager.createNewUser(form);
    }
}
