<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Scholarship List</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
}

.main-content {
    flex: 1;
    padding: 20px;
}

.container {
    width: 70%;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-left:300px;
}

h2 {
    color: #1E3A8A;
    text-align: center;
    margin-top: 0;
}

a {
    color: #1E3A8A;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

.action-link {
    display: inline-block;
    margin-bottom: 15px;
    padding: 8px 15px;
    background-color: #1E3A8A;
    color: white;
    border-radius: 4px;
}

.action-link:hover {
    background-color: #152a60;
    text-decoration: none;
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
    font-weight: bold;
}

tr:hover {
    background-color: #f9f9f9;
}

.table-actions a {
    margin-right: 10px;
}

.error {
    color: red;
    margin-top: 10px;
    padding: 10px;
    background-color: #ffebee;
    border-radius: 4px;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>
<div class="main-content">

    <div class="container">
        <h2>Scholarship List</h2>
        <a href="<%= request.getContextPath() %>/admin/scholarships?action=create" class="action-link">Create New Scholarship</a>
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Amount</th>
                    <th>Deadline</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
            java.util.List<com.meritfunds.models.Scholarship> scholarships = (java.util.List<com.meritfunds.models.Scholarship>) request.getAttribute("scholarships");
            if (scholarships != null && !scholarships.isEmpty()) {
                for (com.meritfunds.models.Scholarship scholarship : scholarships) {
            %>
                <tr>
                    <td><%= scholarship.getTitle() %></td>
                    <td><%= scholarship.getAmount() %></td>
                    <td><%= scholarship.getDeadline() %></td>
                    <td class="table-actions">
                        <a href="<%= request.getContextPath() %>/admin/scholarships?action=edit&id=<%= scholarship.getScholarshipId() %>">Edit</a>
                        <a href="<%= request.getContextPath() %>/admin/scholarships?action=delete&id=<%= scholarship.getScholarshipId() %>">Delete</a>
                    </td>
                </tr>
            <%
                }
            } else {
            %>
                <tr>
                    <td colspan="4">No scholarships found.</td>
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
</div>
</body>
</html>