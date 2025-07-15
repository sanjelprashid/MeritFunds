package com.meritfunds.services;

import com.meritfunds.models.Scholarship;
import com.meritfunds.config.DbConfig; // Import DbConfig
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScholarshipServices {

    // Method to get a connection to the database using DbConfig
    protected Connection getConnection() throws SQLException {
        return DbConfig.getConnection();
    }

    // Method to insert scholarship
    public boolean createScholarship(Scholarship scholarship) {
        String sql = "INSERT INTO scholarships (title, description, amount, eligibility, deadline, created_by) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, scholarship.getTitle());
            preparedStatement.setString(2, scholarship.getDescription());
            preparedStatement.setDouble(3, scholarship.getAmount());
            preparedStatement.setString(4, scholarship.getEligibility());
            preparedStatement.setTimestamp(5, scholarship.getDeadline());
            preparedStatement.setInt(6, scholarship.getCreatedBy());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update scholarship
    public boolean updateScholarship(Scholarship scholarship) {
        String sql = "UPDATE scholarships SET title = ?, description = ?, amount = ?, eligibility = ?, deadline = ? WHERE scholarship_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, scholarship.getTitle());
            preparedStatement.setString(2, scholarship.getDescription());
            preparedStatement.setDouble(3, scholarship.getAmount());
            preparedStatement.setString(4, scholarship.getEligibility());
            preparedStatement.setTimestamp(5, scholarship.getDeadline());
            preparedStatement.setInt(6, scholarship.getScholarshipId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete scholarship
    public boolean deleteScholarship(int scholarshipId) {
        String sql = "DELETE FROM scholarships WHERE scholarship_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, scholarshipId);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get scholarship by ID
    public Scholarship getScholarshipById(int scholarshipId) {
        Scholarship scholarship = null;
        String sql = "SELECT * FROM scholarships WHERE scholarship_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, scholarshipId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                scholarship = new Scholarship();
                scholarship.setScholarshipId(resultSet.getInt("scholarship_id"));
                scholarship.setTitle(resultSet.getString("title"));
                scholarship.setDescription(resultSet.getString("description"));
                scholarship.setAmount(resultSet.getDouble("amount"));
                scholarship.setEligibility(resultSet.getString("eligibility"));
                scholarship.setDeadline(resultSet.getTimestamp("deadline"));
                scholarship.setCreatedBy(resultSet.getInt("created_by"));
                scholarship.setCreatedAt(resultSet.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scholarship;
    }

    // Method to get all scholarships
    public List<Scholarship> getAllScholarships() {
        List<Scholarship> scholarships = new ArrayList<>();
        String sql = "SELECT * FROM scholarships";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Scholarship scholarship = new Scholarship();
                scholarship.setScholarshipId(resultSet.getInt("scholarship_id"));
                scholarship.setTitle(resultSet.getString("title"));
                scholarship.setDescription(resultSet.getString("description"));
                scholarship.setAmount(resultSet.getDouble("amount"));
                scholarship.setEligibility(resultSet.getString("eligibility"));
                scholarship.setDeadline(resultSet.getTimestamp("deadline"));
                scholarship.setCreatedBy(resultSet.getInt("created_by"));
                scholarship.setCreatedAt(resultSet.getTimestamp("created_at"));
                scholarships.add(scholarship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scholarships;
    }
}