package ru.innopolis.data;

import ru.innopolis.entity.Account;

/**
 * Created by ibrahim on 7/5/2017.
 */
public interface IAccount {
    Long createNewAccount(Account account);
    Long findAccountId(Account account);
}
