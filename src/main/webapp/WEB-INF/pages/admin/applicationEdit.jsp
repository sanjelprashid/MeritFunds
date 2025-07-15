<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>
<%@ page import="com.meritfunds.models.ApplicationScholarship" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Application Status</title>
     <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
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

        label {
            display: block;
            margin-bottom: 0.5rem;
            color: #555;
            font-weight: 500;
        }

        select {
            width: 100%;
            padding: 0.75rem;
            margin-bottom: 1.5rem;
            border: 1px solid #ddd;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 1rem;
        }

        button[type="submit"] {
            background-color: #2563EB;
            color: white;
            padding: 0.8rem 1.5rem;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #1E3A8A;
        }

        .error {
            color: #e44d26;
            margin-top: 1rem;
            text-align: center;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 1.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Application Status</h2>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <form action="<%= request.getContextPath() %>/admin/applications" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="applicationId" value="<%= ((ApplicationScholarship) request.getAttribute("application")).getApplicationId() %>">

            <label for="status">Status:</label>
            <select id="status" name="status">
                <option value="Pending" <%= ((ApplicationScholarship) request.getAttribute("application")).getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                <option value="Approved" <%= ((ApplicationScholarship) request.getAttribute("application")).getStatus().equals("Approved") ? "selected" : "" %>>Approved</option>
                <option value="Rejected" <%= ((ApplicationScholarship) request.getAttribute("application")).getStatus().equals("Rejected") ? "selected" : "" %>>Rejected</option>
            </select>

            <button type="submit">Update Status</button>
        </form>
    </div>
</body>
</html>