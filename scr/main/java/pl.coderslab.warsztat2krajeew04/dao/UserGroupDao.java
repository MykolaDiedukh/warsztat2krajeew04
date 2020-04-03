package pl.coderslab.warsztat2krajeew04.dao;

import pl.coderslab.warsztat2krajeew04.model.UserGroup;
import pl.coderslab.warsztat2krajeew04.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao {
    private static final String CREAT_USER_QUERY = "INSERT INTO users_group (name) VALUE (?);";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users_group;";
    private static final String READ_USER_BY_ID_QUERY = "SELECT * FROM users_group where id = ?;";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users_group where id = ?;";
    private static final String UPDATE_USER_QUERY = "UPDATE users_group SET name = ? WHERE id = ?;";

    /**
     * Create users group
     *
     * @param userGroup
     * @return
     */
    public UserGroup create(UserGroup userGroup) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_USER_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, userGroup.getName());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    userGroup.setId(generatedKeys.getInt(1));
                    return userGroup;
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
     * Return all users group
     *
     * @return
     */
    public List<UserGroup> findAll() {
        List<UserGroup> userGroupList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UserGroup userGroupToAdd = new UserGroup();
                userGroupToAdd.setId(resultSet.getInt("id"));
                userGroupToAdd.setName(resultSet.getString("name"));
                userGroupList.add(userGroupToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userGroupList;
    }

    /**
     * Get users group by id
     *
     * @param id
     * @return
     */
    public UserGroup readById(int id) {
        UserGroup userGroup = new UserGroup();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_USER_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userGroup.setId(resultSet.getInt("id"));
                    userGroup.setName(resultSet.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userGroup;
    }

    /**
     * Remove users group by id
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
     * Update users group
     *
     * @param user
     */
    public void update(UserGroup user) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            statement.setInt(2, user.getId());
            statement.setString(1, user.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
