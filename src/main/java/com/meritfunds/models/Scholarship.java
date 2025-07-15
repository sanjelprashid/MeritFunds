package com.meritfunds.models;

import java.sql.Timestamp;

public class Scholarship {
    private int scholarshipId;
    private String title;
    private String description;
    private double amount;
    private String eligibility;
    private Timestamp deadline;
    private int createdBy; // User ID of the admin who created the scholarship
    private Timestamp createdAt;

    public Scholarship() {
    }

    public Scholarship(int scholarshipId, String title, String description, double amount, String eligibility, Timestamp deadline, int createdBy, Timestamp createdAt) {
        this.scholarshipId = scholarshipId;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.eligibility = eligibility;
        this.deadline = deadline;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

     public Scholarship(String title, String description, double amount, String eligibility, Timestamp deadline, int createdBy) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.eligibility = eligibility;
        this.deadline = deadline;
        this.createdBy = createdBy;
    }


    // Getters
    public int getScholarshipId() {
        return scholarshipId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getEligibility() {
        return eligibility;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setScholarshipId(int scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

      @Override
    public String toString() {
        return "Scholarship{" +
                "scholarshipId=" + scholarshipId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", eligibility='" + eligibility + '\'' +
                ", deadline=" + deadline +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}