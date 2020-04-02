package pl.coderslab.warsztat2krajeew04.dao;

import pl.coderslab.warsztat2krajeew04.model.User;
import pl.coderslab.warsztat2krajeew04.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String CREAT_USER_QUERY = "INSERT INTO users (username, email, password, user_group_id) VALUES (?,?,?,?);";
    private static final String FIND_ALL_USER_QUERY = "SELECT * FROM users;";
    private static final String READ_USER_BY_ID_QUERY = "SELECT * FROM users where id = ?;";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users where id = ?;";
    private static final String UPDATE_USER_QUERY= "UPDATE users SET username = ? , email = ?, password = ?, user_group_id = ? WHERE id = ?;";

    /**
     * Create user
     *
     * @param user
     * @return
     */
    public User createUser(User user) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_USER_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, user.getUsername());
            insertStm.setString(2, user.getEmail());
            insertStm.setString(3, user.getPassword());
            insertStm.setInt(4, user.getUserGroupId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    user.setId(generatedKeys.getInt(1));
                    return user;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return all users
     *
     * @return
     */
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User userToAdd = new User();
                userToAdd.setId(resultSet.getInt("id"));
                userToAdd.setUsername(resultSet.getString("username"));
                userToAdd.setEmail(resultSet.getString("email"));
                userToAdd.setPasswordString(resultSet.getString("password"));
                userToAdd.setUserGroupId(resultSet.getInt("user_group_id"));
                userList.add(userToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Get user by id
     *
     * @param id
     * @return
     */
    public User readById(int id) {
        User user = new User();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_USER_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPasswordString(resultSet.getString("password"));
                    user.setUserGroupId(resultSet.getInt("user_group_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Remove user by id
     *
     * @param id
     */
    public void deleteById(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update user
     *
     * @param user
     */
    public void update(User user) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            statement.setInt(5, user.getId());
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroupId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return all users by groupId
     *
     * @param groupId
     * @return
     */
    public List<User> findAllByGroupId(int groupId) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_USER_BY_ID_QUERY)
        ) {
            statement.setInt(1, groupId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPasswordString(resultSet.getString("password"));
                    user.setUserGroupId(resultSet.getInt("user_group_id"));
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
