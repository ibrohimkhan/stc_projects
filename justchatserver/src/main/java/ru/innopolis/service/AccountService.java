package ru.innopolis.service;

import ru.innopolis.data.AccountJDBC;
import ru.innopolis.data.IAccount;
import ru.innopolis.entity.Account;

/**
 * Created by ibrahim on 7/5/2017.
 */
public class AccountService {
    private static IAccount accountJDBC = AccountJDBC.getInstance();

    public static Long createNewAccount(Account account) {
        return accountJDBC.createNewAccount(account);
    }
}
