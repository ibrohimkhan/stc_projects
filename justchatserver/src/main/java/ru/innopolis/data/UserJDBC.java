package ru.innopolis.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.innopolis.entity.Account;
import ru.innopolis.entity.User;

/**
 * Created by ibrahim on 7/5/2017.
 */
public class UserJDBC implements IUser {
    private Sql2o sql2o = ConnectionManager.getInstance().sql2o();

    private UserJDBC() {}

    private static class Holder {
        private static final UserJDBC INSTANCE = new UserJDBC();
    }

    public static UserJDBC getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Long createNewUser(User user) {
        String sql = "insert into chatuser(firstname, lastname, account_id) values (:firstname, :lastname, :account_id)";
        Long id = -1l;

        try (Connection connection = sql2o.open()) {
            id = (Long) connection.createQuery(sql, true)
                    .addParameter("firstname", user.getFirstName())
                    .addParameter("lastname", user.getLastName())
                    .addParameter("account_id", user.getAccount().getId().longValue())
                    .executeUpdate()
                    .getKey();
        }

        return id;
    }

    @Override
    public User findUserByAccount(Account account) {
        String sql = "select * from chatuser where account_id = :id";
        User user = null;

        try (Connection connection = sql2o.open()) {
            user = connection.createQuery(sql).addParameter("id", account.getId()).executeAndFetch(User.class).get(0);
        }

        return user;
    }
}