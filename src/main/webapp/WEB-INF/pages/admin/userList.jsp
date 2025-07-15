<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.meritfunds.models.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
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

        .user-list {
        }

        .user-list table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        .user-list th, .user-list td {
            padding: 0.75rem 1rem;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .user-list th {
            background-color: #f0f0f0;
            font-weight: 500;
        }

         .user-list .actions a {
            display: inline-block;
            padding: 0.5rem 1rem;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .user-list .actions a.delete-button {
             background-color: #e44d26;
             color: white;
         }
         .user-list .actions a.delete-button:hover {
             background-color: #c63917;
         }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 1.5rem;
            }

            .user-list th, .user-list td {
                padding: 0.5rem 0.75rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User List</h2>
        <div class="user-list">
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<com.meritfunds.models.User> users = (List<com.meritfunds.models.User>) request.getAttribute("users");
                if (users != null) {
                    for (com.meritfunds.models.User user : users) {
                %>
                <tr>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                    <td class="actions">
                        <a href="<%= request.getContextPath() %>/admin/users?action=delete&id=<%= user.getUserId() %>" class="delete-button">Delete</a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="4">No users found.</td>
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