package ru.innopolis.data;

import ru.innopolis.entity.Account;
import ru.innopolis.entity.State;
import ru.innopolis.entity.User;

/**
 * Created by ibrahim on 7/5/2017.
 */
public interface IUser {
    Long createNewUser(User user);
    User findUserByAccount(Account account);
    void updateUser(User user);
}
