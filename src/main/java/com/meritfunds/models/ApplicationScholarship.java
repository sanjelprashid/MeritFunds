package com.meritfunds.models;

import java.sql.Timestamp;

public class ApplicationScholarship {
    private int applicationId;
    private int userId;
    private int scholarshipId;
    private Timestamp applicationDate;
    private String status;
    private String applicationImage; // path to application image

    // Constructors
    public ApplicationScholarship() {
    }

    public ApplicationScholarship(int userId, int scholarshipId, String status, String applicationImage) {
        this.userId = userId;
        this.scholarshipId = scholarshipId;
        this.status = status;
        this.applicationImage = applicationImage;
    }

    public ApplicationScholarship(int applicationId, int userId, int scholarshipId, Timestamp applicationDate, String status, String applicationImage) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.scholarshipId = scholarshipId;
        this.applicationDate = applicationDate;
        this.status = status;
        this.applicationImage = applicationImage;
    }

    // Getters
    public int getApplicationId() {
        return applicationId;
    }

    public int getUserId() {
        return userId;
    }

    public int getScholarshipId() {
        return scholarshipId;
    }

    public Timestamp getApplicationDate() {
        return applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getApplicationImage() {
        return applicationImage;
    }

    // Setters
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setScholarshipId(int scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public void setApplicationDate(Timestamp applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApplicationImage(String applicationImage) {
        this.applicationImage = applicationImage;
    }
    @Override
    public String toString() {
        return "ApplicationScholarship{" +
                "applicationId=" + applicationId +
                ", userId=" + userId +
                ", scholarshipId=" + scholarshipId +
                ", applicationDate=" + applicationDate +
                ", status='" + status + '\'' +
                ", applicationImage='" + applicationImage + '\'' +
                '}';
    }
}