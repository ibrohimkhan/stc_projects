package ru.innopolis.service;

import ru.innopolis.data.IUser;
import ru.innopolis.data.UserJDBC;
import ru.innopolis.entity.Account;
import ru.innopolis.entity.User;

/**
 * Created by ibrahim on 7/5/2017.
 */
public class UserService {
    private static IUser userJDBC = UserJDBC.getInstance();

    public static Long createNewUser(User user) {
        return userJDBC.createNewUser(user);
    }

    public static User findUserByAccount(Account account) {
        return userJDBC.findUserByAccount(account);
    }
}
