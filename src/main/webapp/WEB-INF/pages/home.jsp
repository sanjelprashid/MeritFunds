<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Available Scholarships</title>
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
            max-width: 1200px;
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

        .scholarship-list {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 2rem;
            justify-content: center;
        }

        .scholarship-card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
            overflow: hidden;
            transition: transform 0.2s ease-in-out;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            padding: 1.5rem;
        }

        .scholarship-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
        }

        .scholarship-card h3 {
            color: #1E3A8A;
            font-size: 1.5rem;
            margin-bottom: 0.75rem;
            font-weight: 600;
        }

        .scholarship-card p {
            margin-bottom: 1rem;
            font-size: 0.9rem;
            color: #555;
        }

        .scholarship-card a {
            display: inline-block;
            padding: 0.75rem 1.25rem;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s ease, color 0.3s ease;
            text-align: center;
            margin-right: 0.5rem;
        }

        .scholarship-card a.view-details {
            background-color: #DBEAFE;
            color: #1E3A8A;
            border: 1px solid #93C5FD;
        }

        .scholarship-card a.view-details:hover {
            background-color: #93C5FD;
            color: #1E3A8A;
        }

        .scholarship-card a.apply-button {
            background-color: #4CAF50; /* Green color for apply button */
            color: white;
        }

        .scholarship-card a.apply-button:hover {
            background-color: #388E3C;
        }

        .no-scholarships {
            text-align: center;
            font-style: italic;
            color: #777;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 1.5rem;
            }

            .scholarship-list {
                grid-template-columns: 1fr;
            }

            .scholarship-card {
                padding: 1rem;
            }

            .scholarship-card h3 {
                font-size: 1.25rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Available Scholarships</h2>
        <div class="scholarship-list">
            <%
            java.util.List<com.meritfunds.models.Scholarship> scholarships = (java.util.List<com.meritfunds.models.Scholarship>) request.getAttribute("scholarships");
            if (scholarships != null && !scholarships.isEmpty()) {
                for (com.meritfunds.models.Scholarship scholarship : scholarships) {
            %>
            <div class="scholarship-card">
                <h3><%= scholarship.getTitle() %></h3>
                <p><%= scholarship.getDescription() %></p>
                <p>Amount: $<%= scholarship.getAmount() %></p>
                <div>
                  <a href="<%= request.getContextPath() %>/home?action=view&id=<%= scholarship.getScholarshipId() %>" class="view-details">View Details</a>
                  <a href="<%= request.getContextPath() %>/applications?action=apply&scholarshipId=<%= scholarship.getScholarshipId() %>" class="apply-button">Apply</a>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p class="no-scholarships">No scholarships found.</p>
            <%
            }
            %>
        </div>
    </div>
</body>
</html>