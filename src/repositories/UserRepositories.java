package repositories;

import java.sql.*;
import java.util.*;

import models.User;
import utils.ApplicationProperties;

/*
    Layer #3: Data Access
*/
public class UserRepository {
    public List<User> getUsers() {
        List<User> listOfUsers = new ArrayList<>();
        String query = "SELECT * FROM users;";

        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL, ApplicationProperties.USERNAME, ApplicationProperties.PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = mapToUser(resultSet);
                listOfUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfUsers;
    }

    public List<User> getUsersInProject(Long projectId) {
        List<User> listOfUsers = new ArrayList<>();
        String query = "SELECT users.* FROM projects_users JOIN users ON users.user_id = projects_users.user_id WHERE projects_users.project_id = ?;";
        // Connecting to a database steps:
        // 1. Get Connection using DriverManager via jdbc_url, username, and password
        // 2. Get PreparedStatement from Connection instance passing query
        // 3. Execute PreparedStatement query and get the ResultSet
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL, ApplicationProperties.USERNAME, ApplicationProperties.PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, projectId);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = mapToUser(resultSet);
                listOfUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfUsers;
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("userId_column");
        String username = resultSet.getString("username_column");
        String password = resultSet.getString("password_column");
        User user = new User(userId, username, password);
        return user;
    }
}