<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Scholarship Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 60%;
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

        .scholarship-details {
            margin-top: 20px;
        }

        .scholarship-details h3 {
            color: #1E3A8A;
            margin-bottom: 10px;
        }

        .scholarship-details p {
            margin-bottom: 15px;
        }

        .scholarship-details .amount {
            font-weight: bold;
        }

        .scholarship-details .deadline {
            font-style: italic;
        }

        .back-link {
            display: block;
            margin-top: 20px;
            color: #1E3A8A;
            text-decoration: none;
        }
          .header {
            background-color: #1E3A8A; /* Royal Blue */
            color: #fff;
            padding: 20px;
            text-align: center;
            border-bottom: 2px solid #ddd;
        }

        .header h1 {
            margin: 0;
        }

        .header nav ul {
            list-style: none;
            padding: 0;
            margin: 10px 0 0 0;
        }

        .header nav li {
            display: inline;
            margin: 0 15px;
        }

        .header nav a {
            color: #fff;
            text-decoration: none;
        }

        .header nav a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Scholarship Details</h2>
        <%
        com.meritfunds.models.Scholarship scholarship = (com.meritfunds.models.Scholarship) request.getAttribute("scholarship");
        if (scholarship != null) {
        %>
        <div class="scholarship-details">
            <h3><%= scholarship.getTitle() %></h3>
            <p><%= scholarship.getDescription() %></p>
            <p class="amount">Amount: <%= scholarship.getAmount() %></p>
            <p>Eligibility: <%= scholarship.getEligibility() %></p>
            <p class="deadline">Deadline: <%= scholarship.getDeadline() %></p>
        </div>
        <%
        } else {
        %>
        <p>Scholarship not found.</p>
        <%
        }
        %>
        <a class="back-link" href="<%= request.getContextPath() %>/home">Back to Scholarship List</a>
    </div>
</body>
</html>