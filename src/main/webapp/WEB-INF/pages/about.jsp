<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>About Us</title>
    <style>
         body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            color: #333;
            line-height: 1.6;
        }

        .container {
            max-width: 960px;
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

        p {
            margin-bottom: 1.5rem;
            font-size: 1.1rem;
            color: #555;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            margin-bottom: 0.75rem;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 1.5rem;
            }

            p {
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>About Us</h2>
        <p>Merit Funds is dedicated to connecting talented students with the resources they need to achieve their educational dreams. We believe that financial constraints should never be a barrier to accessing quality education.</p>

        <p>Our platform provides a comprehensive listing of scholarships from various organizations, making it easy for students to find opportunities that align with their qualifications and aspirations.</p>

        <p><strong>Our Mission:</strong> To empower students through access to scholarships and financial aid, fostering a brighter future for all.</p>

        <p><strong>Our Team:</strong>
            <ul>
                <li>John Doe - Founder & CEO</li>
                <li>Jane Smith - Scholarship Coordinator</li>
                <li>Peter Jones - Technology Lead</li>
            </ul>
        </p>
    </div>
</body>
</html>