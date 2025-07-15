<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Scholarship</title>
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

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="number"],
        textarea,
        input[type="datetime-local"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #1E3A8A;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #284b63;
        }

        .error {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    <div class="container">
        <h2>Edit Scholarship</h2>
        <form action="<%= request.getContextPath() %>/admin/scholarships" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="scholarshipId" value="<%= ((com.meritfunds.models.Scholarship) request.getAttribute("scholarship")).getScholarshipId() %>">

            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="<%= ((com.meritfunds.models.Scholarship) request.getAttribute("scholarship")).getTitle() %>" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description"><%= ((com.meritfunds.models.Scholarship) request.getAttribute("scholarship")).getDescription() %></textarea>

            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="amount" step="0.01" value="<%= ((com.meritfunds.models.Scholarship) request.getAttribute("scholarship")).getAmount() %>" required>

            <label for="eligibility">Eligibility:</label>
            <textarea id="eligibility" name="eligibility"><%= ((com.meritfunds.models.Scholarship) request.getAttribute("scholarship")).getEligibility() %></textarea>

            <label for="deadline">Deadline:</label>
            <input type="datetime-local" id="deadline" name="deadline" value="<%= ((com.meritfunds.models.Scholarship) request.getAttribute("scholarship")).getDeadline() %>" required>

            <input type="submit" value="Save">
        </form>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</body>
</html>