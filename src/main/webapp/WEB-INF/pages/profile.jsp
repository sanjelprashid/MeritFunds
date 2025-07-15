<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
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

        input[type="text"],
        input[type="email"],
        input[type="file"] {
            width: 100%;
            padding: 0.75rem;
            margin-bottom: 1.5rem;
            border: 1px solid #ddd;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 1rem;
        }

        input[type="submit"] {
            background-color: #2563EB;
            color: white;
            padding: 0.8rem 1.5rem;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #1E3A8A;
        }

        .error {
            color: #e44d26;
            margin-top: 1rem;
            text-align: center;
        }

        .profile-image-container {
            text-align: center;
            margin-bottom: 1.5rem;
        }

        .profile-image {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid #ddd;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Profile</h2>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <form action="<%= request.getContextPath() %>/profile" method="post" enctype="multipart/form-data">
            <div class="profile-image-container">
                <img src="<%= request.getContextPath() %>/uploads/<%= ((com.meritfunds.models.User) request.getAttribute("user")).getProfileImage() %>" alt="Profile Image" class="profile-image">
            </div>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= ((com.meritfunds.models.User) request.getAttribute("user")).getName() %>" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= ((com.meritfunds.models.User) request.getAttribute("user")).getEmail() %>" required>

            <label for="profileImage">Update Profile Image:</label>
            <input type="file" id="profileImage" name="profileImage" accept="image/*">

            <input type="submit" value="Update Profile">
        </form>
    </div>
</body>
</html>