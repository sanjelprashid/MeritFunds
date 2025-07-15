package com.meritfunds.models;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String profileImage;
    private Timestamp registeredAt;

    public User(int userId, String name, String email, String password, String role, String profileImage, Timestamp registeredAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profileImage = profileImage;
        this.registeredAt = registeredAt;
    }

    public User(String name, String email, String password, String role, String profileImage) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profileImage = profileImage;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    // Optional: toString() method for debugging/logging
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", role='" + role + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", registeredAt=" + registeredAt +
                '}';
    }
}