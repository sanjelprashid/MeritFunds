package com.meritfunds.services;

import com.meritfunds.config.DbConfig;
import com.meritfunds.models.ApplicationScholarship;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationServices {

    // Method to create a new application
    public boolean createApplication(ApplicationScholarship application) {
        String sql = "INSERT INTO applications (user_id, scholarship_id, status, application_image) VALUES (?, ?, ?, ?)";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, application.getUserId());
            preparedStatement.setInt(2, application.getScholarshipId());
            preparedStatement.setString(3, application.getStatus());
            preparedStatement.setString(4, application.getApplicationImage());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get an application by ID
    public ApplicationScholarship getApplicationById(int applicationId) {
        ApplicationScholarship application = null;
        String sql = "SELECT * FROM applications WHERE application_id = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, applicationId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                application = new ApplicationScholarship();
                application.setApplicationId(resultSet.getInt("application_id"));
                application.setUserId(resultSet.getInt("user_id"));
                application.setScholarshipId(resultSet.getInt("scholarship_id"));
                application.setApplicationDate(resultSet.getTimestamp("application_date"));
                application.setStatus(resultSet.getString("status"));
                application.setApplicationImage(resultSet.getString("application_image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }
        // Method to get all applications
    public List<ApplicationScholarship> getAllApplications() {
        List<ApplicationScholarship> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications";
        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                ApplicationScholarship application = new ApplicationScholarship();
                 application.setApplicationId(resultSet.getInt("application_id"));
                application.setUserId(resultSet.getInt("user_id"));
                application.setScholarshipId(resultSet.getInt("scholarship_id"));
                application.setApplicationDate(resultSet.getTimestamp("application_date"));
                application.setStatus(resultSet.getString("status"));
                application.setApplicationImage(resultSet.getString("application_image"));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }


    // Method to update application status
    public boolean updateApplicationStatus(int applicationId, String status) {
        String sql = "UPDATE applications SET status = ? WHERE application_id = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, applicationId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get applications by scholarship ID
    public List<ApplicationScholarship> getApplicationsByScholarshipId(int scholarshipId) {
        List<ApplicationScholarship> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE scholarship_id = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, scholarshipId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ApplicationScholarship application = new ApplicationScholarship();
                application.setApplicationId(resultSet.getInt("application_id"));
                application.setUserId(resultSet.getInt("user_id"));
                application.setScholarshipId(resultSet.getInt("scholarship_id"));
                application.setApplicationDate(resultSet.getTimestamp("application_date"));
                application.setStatus(resultSet.getString("status"));
                application.setApplicationImage(resultSet.getString("application_image"));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    // Method to get applications by user ID
    public List<ApplicationScholarship> getApplicationsByUserId(int userId) {
        List<ApplicationScholarship> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE user_id = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ApplicationScholarship application = new ApplicationScholarship();
                 application.setApplicationId(resultSet.getInt("application_id"));
                application.setUserId(resultSet.getInt("user_id"));
                application.setScholarshipId(resultSet.getInt("scholarship_id"));
                application.setApplicationDate(resultSet.getTimestamp("application_date"));
                application.setStatus(resultSet.getString("status"));
                application.setApplicationImage(resultSet.getString("application_image"));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }
    
    // Get recent application function to get recent application list
    public List<ApplicationScholarship> getRecentApplications(int limit) {
      String sql = "SELECT * FROM applications ORDER BY application_date DESC LIMIT ?";
      List<ApplicationScholarship> recentApplications = new ArrayList<>();

      try (Connection connection = DbConfig.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

          preparedStatement.setInt(1, limit);
          ResultSet resultSet = preparedStatement.executeQuery();

          while (resultSet.next()) {
             ApplicationScholarship application = new ApplicationScholarship();
              application.setApplicationId(resultSet.getInt("application_id"));
              application.setUserId(resultSet.getInt("user_id"));
              application.setScholarshipId(resultSet.getInt("scholarship_id"));
              application.setApplicationDate(resultSet.getTimestamp("application_date"));
              application.setStatus(resultSet.getString("status"));
              application.setApplicationImage(resultSet.getString("application_image"));
              recentApplications.add(application);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return recentApplications;
  }
}
