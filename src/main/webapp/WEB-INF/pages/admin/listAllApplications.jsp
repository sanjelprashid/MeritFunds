<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.meritfunds.models.ApplicationScholarship" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - All Application List</title>
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

        .application-list {
        }

        .application-list table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        .application-list th, .application-list td {
            padding: 0.75rem 1rem;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .application-list th {
            background-color: #f0f0f0;
            font-weight: 500;
        }

         .application-list .actions a {
            display: inline-block;
            padding: 0.5rem 1rem;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s ease, color 0.3s ease;
            margin-right: 0.5rem;
        }

        .application-list .actions a.edit-button {
             background-color: #3498db;
             color: white;
         }
         .application-list .actions a.edit-button:hover {
             background-color: #2980b9;
         }

        .application-list .actions a.delete-button {
             background-color: #e44d26;
             color: white;
         }
         .application-list .actions a.delete-button:hover {
             background-color: #c63917;
         }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 1.5rem;
            }

            .application-list th, .application-list td {
                padding: 0.5rem 0.75rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Admin - All Application List</h2>
        <div class="application-list">
        <table>
            <thead>
                <tr>
                    <th>Application ID</th>
                    <th>User ID</th>
                    <th>Scholarship ID</th>
                    <th>Application Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<com.meritfunds.models.ApplicationScholarship> applications = (List<com.meritfunds.models.ApplicationScholarship>) request.getAttribute("applications");
                if (applications != null) {
                    for (com.meritfunds.models.ApplicationScholarship applicatio : applications) {
                %>
                <tr>
                    <td><%= applicatio.getApplicationId() %></td>
                    <td><%= applicatio.getUserId() %></td>
                    <td><%= applicatio.getScholarshipId() %></td>
                    <td><%= applicatio.getApplicationDate() %></td>
                    <td><%= applicatio.getStatus() %></td>
                    <td class = "actions">
                         <a href="<%= request.getContextPath() %>/admin/applications?action=edit&id=<%= applicatio.getApplicationId() %>" class="edit-button">Edit</a>
                         <!--
                        <a href="<%= request.getContextPath() %>/admin/applications?action=delete&id=<%= applicatio.getApplicationId() %>" class="delete-button">Delete</a>
                         -->
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6">No applications found.</td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
          </div>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</body>
</html>