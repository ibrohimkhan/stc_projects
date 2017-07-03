package ru.innopolis.data;

import java.util.List;

import ru.innopolis.model.Account;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class AccountDataStore {

    private InitialDataStore dataStore = InitialDataStore.getInstance();
    private AccountDataStore() {}

    private static class Helper {
        private static final AccountDataStore INSTANCE = new AccountDataStore();
    }

    public static AccountDataStore getInstance() {
        return Helper.INSTANCE;
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
