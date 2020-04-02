package pl.coderslab.warsztat2krajeew04.dao;

import pl.coderslab.warsztat2krajeew04.model.Exercise;
import pl.coderslab.warsztat2krajeew04.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {
    private static final String CREAT_EXERCISE_QUERY = "INSERT INTO exercises (title, description) VALUES (?,?);";
    private static final String FIND_ALL_EXERCISE_QUERY = "SELECT * FROM exercises;";
    private static final String READ_EXERCISE_BY_ID_QUERY = "SELECT * FROM exercises where id = ?;";
    private static final String DELETE_EXERCISE_BY_ID_QUERY = "DELETE FROM exercises where id = ?;";
    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercises SET title = ? , description = ? WHERE id = ?;";

    /**
     * Create exercise
     *
     * @param exercise
     * @return
     */
    public Exercise createUser(Exercise exercise) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_EXERCISE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, exercise.getTitle());
            insertStm.setString(2, exercise.getDescription());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    exercise.setId(generatedKeys.getInt(1));
                    return exercise;
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
     * Return all exercises
     *
     * @return
     */
    public List<Exercise> findAll() {
        List<Exercise> exerciseList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXERCISE_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Exercise exerciseToAdd = new Exercise();
                exerciseToAdd.setId(resultSet.getInt("id"));
                exerciseToAdd.setTitle(resultSet.getString("title"));
                exerciseToAdd.setDescription(resultSet.getString("description"));
                exerciseList.add(exerciseToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exerciseList;
    }

    /**
     * Get exercise by id
     *
     * @param id
     * @return
     */
    public Exercise readById(int id) {
        Exercise exercise = new Exercise();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_EXERCISE_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    exercise.setId(resultSet.getInt("id"));
                    exercise.setTitle(resultSet.getString("title"));
                    exercise.setDescription(resultSet.getString("description"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exercise;
    }

    /**
     * Remove exercise by id
     *
     * @param id
     */
    public void deleteById(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EXERCISE_BY_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update user
     *
     * @param exercise
     */
    public void update(Exercise exercise) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EXERCISE_QUERY)) {
            statement.setInt(3, exercise.getId());
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
