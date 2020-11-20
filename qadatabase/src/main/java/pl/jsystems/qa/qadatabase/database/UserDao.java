package pl.jsystems.qa.qadatabase.database;

import pl.jsystems.qa.qadatabase.database.model.UserDb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static List<UserDb> getAllUsers() {
        List<UserDb> userDbs = new ArrayList<>();

        String sql = "select * from testuser";

        Statement statement = null;
        ResultSet wynik = null;
        try {
            statement = DatabaseConnector.getConnection().createStatement();

            wynik = statement.executeQuery(sql);

            while (wynik.next()) {
                UserDb userDb = new UserDb(wynik.getString(1), wynik.getString(2), wynik.getString(3));
                userDbs.add(userDb);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                wynik.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return userDbs;
    }



    public static UserDb getUserById(String id) {

        String sql = "select * from testuser where id = '" + id + "'";
        UserDb userDb = null;

        Statement statement = null;
        ResultSet wynik = null;
        try {
            statement = DatabaseConnector.getConnection().createStatement();
            wynik = statement.executeQuery(sql);

            while (wynik.next()) {
                userDb = new UserDb(wynik.getString("id"), wynik.getString("name"), wynik.getString("surname"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                wynik.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return userDb;
    }

    public static void saveUser(UserDb userDb) {
        String sql = "insert into testuser (id, name, surname) values ('" + userDb.id + "', '" + userDb.name + "', '" + userDb.surname +"')";
        Statement statement = null;
        try {
            statement = DatabaseConnector.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void deleteUser(String id) {
        String sql = "delete testuser where id = '" + id + "'";
        Statement statement = null;
        try {
            statement = DatabaseConnector.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void updateUser(UserDb userDb, String id) {
        String sql = "update testuser set id = '" + userDb.id + "', name = '" + userDb.name + "', surname = '" + userDb.surname + "' where id = '" + id + "'";

        Statement statement = null;
        try {
            statement = DatabaseConnector.getConnection().createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}