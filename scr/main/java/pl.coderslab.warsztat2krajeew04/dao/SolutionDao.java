package pl.coderslab.warsztat2krajeew04.dao;

import pl.coderslab.warsztat2krajeew04.model.Solution;
import pl.coderslab.warsztat2krajeew04.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    private static final String CREAT_SOLUTION_QUERY = "INSERT INTO solutions (created, exercise_id, user_id) VALUES (?,?,?);";
    private static final String FIND_ALL_SOLUTIONS_QUERY = "SELECT * FROM solutions;";
    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY = "SELECT * FROM solutions WHERE user_id = ?;";
    private static final String FIND_ALL_SOLUTIONS_BY_EXERCISE_ID_QUERY = "SELECT * FROM solutions WHERE exercise_id = ? order by  created DESC;";
    private static final String READ_SOLUTION_BY_ID_QUERY = "SELECT * FROM solutions WHERE id = ?;";
    private static final String DELETE_SOLUTION_BY_ID_QUERY = "DELETE FROM solutions WHERE id = ?;";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solutions SET updated = ?, description = ? WHERE id = ?;";

    /**
     * Create solution
     *
     * @param solution
     * @return
     */
    public Solution create(Solution solution) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_SOLUTION_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, solution.getCreated().toString());
            insertStm.setInt(2, solution.getExerciseId());
            insertStm.setInt(3, solution.getUserId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    solution.setId(generatedKeys.getInt(1));
                    return solution;
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
     * Return all solutions
     *
     * @return
     */
    public List<Solution> findAll() {
        List<Solution> solutionList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Solution solutionToAdd = new Solution();
                solutionToAdd.setId(resultSet.getInt("id"));
                solutionToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solutionToAdd.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                solutionToAdd.setDescription(resultSet.getString("description"));
                solutionToAdd.setExerciseId(resultSet.getInt("exercise_id"));
                solutionToAdd.setUserId(resultSet.getInt("user_id"));
                solutionList.add(solutionToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solutionList;
    }

    /**
     * Get solution by id
     *
     * @param id
     * @return
     */
    public Solution readById(int id) {
        Solution solution = new Solution();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_SOLUTION_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    solution.setId(resultSet.getInt("id"));
                    solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                    solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                    solution.setDescription(resultSet.getString("description"));
                    solution.setExerciseId(resultSet.getInt("exercise_id"));
                    solution.setUserId(resultSet.getInt("user_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solution;
    }

    /**
     * Remove solution by id
     *
     * @param id
     */
    public void deleteById(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SOLUTION_BY_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update solution
     *
     * @param solution
     */
    public void update(Solution solution) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SOLUTION_QUERY)) {
            statement.setInt(3, solution.getId());
            statement.setString(1, solution.getUpdated().toString());
            statement.setString(2, solution.getDescription());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return all solutions by userId
     * @param userId
     * @return
     */
    public List<Solution> findAllByUserId(int userId) {
        List<Solution> solutionList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY)
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Solution solutionToAdd = new Solution();
                    solutionToAdd.setId(resultSet.getInt("id"));
                    solutionToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                    if(resultSet.getTimestamp("updated")!=null) {
                        solutionToAdd.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                    }
                    solutionToAdd.setDescription(resultSet.getString("description"));
                    solutionToAdd.setExerciseId(resultSet.getInt("exercise_id"));
                    solutionToAdd.setUserId(resultSet.getInt("user_id"));
                    solutionList.add(solutionToAdd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solutionList;
    }

    /**
     * Return all solutions by exerciseId
     * @param exerciseId
     * @return
     */
    public List<Solution> findAllByExerciseId(int exerciseId) {
        List<Solution> solutionList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTIONS_BY_EXERCISE_ID_QUERY)
        ) {
            statement.setInt(1, exerciseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Solution solutionToAdd = new Solution();
                    solutionToAdd.setId(resultSet.getInt("id"));
                    solutionToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                    solutionToAdd.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                    solutionToAdd.setDescription(resultSet.getString("description"));
                    solutionToAdd.setExerciseId(resultSet.getInt("exercise_id"));
                    solutionToAdd.setUserId(resultSet.getInt("user_id"));
                    solutionList.add(solutionToAdd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solutionList;
    }
}
