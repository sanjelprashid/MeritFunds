<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.meritfunds.models.User" %>
<%@ page import="com.meritfunds.models.ApplicationScholarship" %>
<%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
       <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 2rem auto;
            background-color: #fff;
            padding: 2rem;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
              margin-left: 250px; /* Adjusted for sidebar */
        }

        h2 {
            color: #2563EB;
            text-align: center;
            margin-bottom: 2rem;
            font-weight: 600;
            letter-spacing: 0.5px;
        }

        .dashboard-section {
            margin-bottom: 2rem;
        }

        .dashboard-section h3 {
            color: #1E3A8A;
            font-size: 1.4rem;
            margin-bottom: 1rem;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        th, td {
            padding: 0.75rem 1rem;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f0f0f0;
            font-weight: 500;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 1.5rem;
            }

            th, td {
                padding: 0.5rem 0.75rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Admin Dashboard</h2>

        <div class="dashboard-section">
            <h3>Recent Sign-ups</h3>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Registered At</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    List<com.meritfunds.models.User> recentUsers = (List<com.meritfunds.models.User>) request.getAttribute("recentUsers");
                    if (recentUsers != null) {
                        for (com.meritfunds.models.User user : recentUsers) {
                    %>
                    <tr>
                        <td><%= user.getName() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getRegisteredAt() %></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="3">No recent sign-ups.</td>
                    </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
        </div>

        <div class="dashboard-section">
            <h3>Recent Applications</h3>
            <table>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Scholarship ID</th>
                        <th>Application Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    List<com.meritfunds.models.ApplicationScholarship> recentApplications = (List<com.meritfunds.models.ApplicationScholarship>) request.getAttribute("recentApplications");
                    if (recentApplications != null) {
                        for (com.meritfunds.models.ApplicationScholarship applications : recentApplications) {
                    %>
                    <tr>
                        <td><%= applications.getUserId() %></td>
                        <td><%= applications.getScholarshipId() %></td>
                        <td><%= applications.getApplicationDate() %></td>
                        <td><%= applications.getStatus() %></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4">No recent applications.</td>
                    </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>