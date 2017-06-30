package ru.innopolis.manager;

import ru.innopolis.data.AccountDataStore;
import ru.innopolis.model.Account;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class AccountManager {

    private static AccountDataStore accountDataStore = AccountDataStore.getInstance();

    public static boolean authenticate(String username, String password) {
        return accountDataStore.authenticate(username, password);
    }

    public static Account registerNewAccount(String username, String password) {
        return accountDataStore.registerNewAccount(username, password);
    }

    public static Account findAccount(String username, String password) {
        return accountDataStore.findAccount(username, password);
    }
}
