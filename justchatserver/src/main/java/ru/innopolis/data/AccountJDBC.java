package ru.innopolis.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.innopolis.entity.Account;

/**
 * Created by ibrahim on 7/5/2017.
 */
public class AccountJDBC implements IAccount {
    private Sql2o sql2o = ConnectionManager.getInstance().sql2o();

    private AccountJDBC() {}

    private static class Holder {
        private static final AccountJDBC INSTANCE = new AccountJDBC();
    }

    public static AccountJDBC getInstance() {
        return Holder.INSTANCE;
    }

    public Long createNewAccount(Account account) {
        String sql = "insert into account(email, password) values(:email, :password)";
        Long id = -1l;

        try (Connection connection = sql2o.open()) {
            id = (Long) connection.createQuery(sql, true)
                    .addParameter("email", account.getEmail())
                    .addParameter("password", account.getPassword())
                    .executeUpdate()
                    .getKey();
        }

        return id;
    }

}
