package ru.innopolis.data;

import org.sql2o.Sql2o;

/**
 * Created by ibrahim on 7/5/2017.
 */
public class ConnectionManager {
    private static final Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/justchat", "postgres", "root");

    private ConnectionManager() {}

    private static class Holder {
        private static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    public static ConnectionManager getInstance() {
        return Holder.INSTANCE;
    }

    public Sql2o sql2o() {
        return sql2o;
    }
}
