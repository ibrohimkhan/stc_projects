package ru.innopolis.service;

import ru.innopolis.data.IUser;
import ru.innopolis.data.UserJDBC;
import ru.innopolis.entity.Account;
import ru.innopolis.entity.State;
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
        if (account == null || account.getEmail() == null || account.getEmail().isEmpty()) return null;
        return userJDBC.findUserByAccount(account);
    }

    public static void updateUser(User user) {
        userJDBC.updateUser(user);
    }
}
