package ru.innopolis.data;

import java.util.List;

import ru.innopolis.model.Account;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class AccountDataStore {
    private static AccountDataStore instance;
    private InitialDataStore dataStore = InitialDataStore.getInstance();

    private AccountDataStore() {}

    public static AccountDataStore getInstance() {
        if (instance == null) {
            synchronized (AccountDataStore.class) {
                if (instance == null) instance = new AccountDataStore();
            }
        }

        return instance;
    }

    public Account registerNewAccount(String username, String password) {
        Account account = new Account(username, password);
        dataStore.registerNewAccount(account);

        return account;
    }

    public boolean authenticate(String username, String password) {
        Account account = findAccount(username, password);
        if (account == null) return false;
        else return true;
    }

    public Account findAccount(String username, String password) {
        List<Account> accounts = dataStore.getAccounts();

        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password))
                return account;
        }

        return null;
    }
}
