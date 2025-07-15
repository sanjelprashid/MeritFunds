<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, com.meritfunds.models.ApplicationScholarship" %>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Applications</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #1E3A8A;
            text-align: center;
        }

        a {
            color: #1E3A8A;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f0f0f0;
        }

        .error {
            color: red;
            margin-top: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>My Applications</h2>
        <table>
            <thead>
                <tr>
                    <th>Scholarship ID</th>
                    <th>Application Date</th>
                    <th>Status</th>
                    <th>Image</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<ApplicationScholarship> applications = (List<ApplicationScholarship>) request.getAttribute("applications");
                    if (applications != null && !applications.isEmpty()) {
                        for (int i = 0; i < applications.size(); i++) {
                            ApplicationScholarship applicationss = applications.get(i);
                %>
                <tr>
                    <td><%= applicationss.getScholarshipId() %></td>
                    <td><%= applicationss.getApplicationDate() %></td>
                    <td><%= applicationss.getStatus() %></td>
                    <td>
                        <%
                            String imageUrl = applicationss.getApplicationImage();
                            if (imageUrl != null && !imageUrl.isEmpty()) {
                        %>
                            <img src="/MeritFunds/uploads/<%= imageUrl %>" alt="Application Image" style="max-width: 100px;">
                        <%
                            } else {
                        %>
                            No image
                        <%
                            }
                        %>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" style="text-align:center;">No applications found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</body>
</html>
