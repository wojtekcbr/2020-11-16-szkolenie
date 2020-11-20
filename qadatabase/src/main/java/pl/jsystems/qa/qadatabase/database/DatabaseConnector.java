package pl.jsystems.qa.qadatabase.database;

import pl.jsystems.qa.qadatabase.configuration.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            initConnection();
        }
        return connection;
    }

    private static void initConnection() {
        try {
            Class.forName(DBConfig.DB_CLASS);
            String url = DBConfig.DB_URL;
            String user = DBConfig.DB_USER;
            String pass = DBConfig.DB_PASS;

            connection = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}