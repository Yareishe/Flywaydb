package org.example;

import java.sql.*;

public class Database {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/init_db";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASS = "";
    private static Database instance;
    private Connection connection;
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
        }
        return connection;
    }
}
