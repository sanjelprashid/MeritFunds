<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.meritfunds.models.User" %>
<%@ page import="com.meritfunds.utils.SessionUtils" %>
<style>
/* Reset some basic elements */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* Modern font stack */
body {
  font-family: 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  line-height: 1.6;
}

.header {
  background-color: #1E3A8A; /* Royal Blue */
  background-image: linear-gradient(to right, #1E3A8A, #2563EB);
  color: #fff;
  padding: 1rem 2rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: relative;
}

.header-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem 0;
}

.logo {
  display: flex;
  align-items: center;
}

.logo h1 {
  font-size: 1.8rem;
  font-weight: 600;
  letter-spacing: 0.5px;
  margin: 0;
}

.logo-accent {
  color: #93C5FD; /* Light blue accent */
  font-weight: 700;
}

.main-nav ul {
  list-style: none;
  display: flex;
  gap: 1.5rem;
  margin: 0;
  padding: 0;
}

.main-nav li {
  position: relative;
}

.main-nav a {
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  font-size: 1rem;
  padding: 0.5rem 0.75rem;
  border-radius: 4px;
  transition: all 0.2s ease;
  display: block;
}

.main-nav a:hover {
  background-color: rgba(255, 255, 255, 0.1);
  transform: translateY(-2px);
}

.main-nav a:active {
  transform: translateY(0);
}

.main-nav a.active {
  background-color: rgba(255, 255, 255, 0.2);
  font-weight: 600;
}

.action-buttons {
  display: flex;
  gap: 0.75rem;
}

.btn {
  padding: 0.5rem 1rem;
  border-radius: 4px;
  font-weight: 500;
  text-decoration: none;
  transition: all 0.2s ease;
}

.btn-primary {
  background-color: #DBEAFE; /* Light blue */
  color: #1E3A8A;
}

.btn-primary:hover {
  background-color: #fff;
  transform: translateY(-2px);
}

.btn-secondary {
  background-color: transparent;
  color: #fff;
  border: 1px solid #DBEAFE;
}

.btn-secondary:hover {
  background-color: rgba(255, 255, 255, 0.1);
  transform: translateY(-2px);
}

/* Responsive adjustments */
@media (max-width: 768px) {
  .header-container {
    flex-direction: column;
    gap: 1rem;
  }
  
  .main-nav ul {
    flex-wrap: wrap;
    justify-content: center;
    gap: 0.75rem;
  }
  
  .action-buttons {
    margin-top: 0.5rem;
  }
}
.profile-section {
  position: relative;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.profile-name {
  color: #fff;
  font-weight: 500;
  margin-right: 0.5rem;
  display: inline-block;
}

.profile-dropdown {
  position: absolute;
  top: 100%;
  right: 0;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 4px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  padding: 0.5rem 0;
  display: none;
  z-index: 10;
}

.profile-dropdown a {
  display: block;
  padding: 0.5rem 1rem;
  text-decoration: none;
  color: #333;
  transition: background-color 0.2s ease;
  text-align: left;
}

.profile-dropdown a:hover {
  background-color: #f4f4f4;
}

.profile-section:hover .profile-dropdown {
  display: block;
}

/* Style for profile image (optional) */
.profile-image {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    margin-right: 8px;
    object-fit: cover; /* ensures the image fills the circular area */
}
</style>

<%
User user = SessionUtils.getCurrentUser(request);
String contextPath = request.getContextPath();
%>
<header class="header">
  <div class="header-container">
    <div class="logo">
      <h1>Merit <span class="logo-accent">Funds</span></h1>
    </div>
    
    <nav class="main-nav">
      <ul>
        <li><a href="<%= contextPath %>/home" class="active">Home</a></li>
        <li><a href="/MeritFunds/about">About</a></li>
        <li><a href="/MeritFunds/contact">Contact</a></li>
        
        <% if (user == null) { // No user logged in %>
          <%-- No specific links --%>
        <% } else if (user.getRole().equals("admin")) { // Admin logged in %>
          <li><a href="<%= contextPath %>/admin/dashboard">Admin Dashboard</a></li>
          <li><a href="<%= contextPath %>/admin/scholarships?action=list">Manage Scholarships</a></li>
          <li><a href="<%= contextPath %>/admin/applications?action=list">Manage Applications</a></li>
        <% } else { // Student logged in %>
          <li><a href="<%= contextPath %>/applications?action=list">My Applications</a></li>
        <% } %>
      </ul>
    </nav>
    
    <div class="action-buttons">
      <% if (user == null) { // No user logged in %>
        <a href="<%= contextPath %>/login" class="btn btn-secondary">Login</a>
        <a href="<%= contextPath %>/register" class="btn btn-primary">Register</a>
      <% } else { %>
        <div class="profile-section">
            <span class="profile-name"><%= user.getName() %></span>
           <div class="profile-dropdown">
                <a href="<%= contextPath %>/profile">Profile</a>
                <a href="<%= contextPath %>/logout">Logout</a>
            </div>
        </div>
      <% } %>
    </div>
  </div>
</header>