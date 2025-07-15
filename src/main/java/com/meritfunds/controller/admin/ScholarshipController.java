package com.meritfunds.controller.admin;

import com.meritfunds.models.Scholarship;
import com.meritfunds.models.User;
import com.meritfunds.services.ScholarshipServices;
import com.meritfunds.utils.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@WebServlet("/admin/scholarships")
public class ScholarshipController extends HttpServlet {

    private final ScholarshipServices scholarshipService = new ScholarshipServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionUtils.getCurrentUser(request);
        if (user == null || !user.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        if (action == null || action.isEmpty() || action.equals("list")) {
            listScholarships(request, response);
        } else if (action.equals("create")) {
            showCreateForm(request, response); // New method to show create form
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
             deleteScholarship(request, response);
        } else {
            listScholarships(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionUtils.getCurrentUser(request);
        if (user == null || !user.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        if ("create".equals(action)) {
            createScholarship(request, response, user);
        } else if ("update".equals(action)) {
            updateScholarship(request, response);
        }  else {
            listScholarships(request, response);
        }
    }

    private void listScholarships(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        request.setAttribute("scholarships", scholarships);
        request.getRequestDispatcher("/WEB-INF/pages/admin/scholarshipList.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/admin/scholarshipCreate.jsp").forward(request, response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int scholarshipId = Integer.parseInt(request.getParameter("id"));
        Scholarship scholarship = scholarshipService.getScholarshipById(scholarshipId);
        request.setAttribute("scholarship", scholarship);
        request.getRequestDispatcher("/WEB-INF/pages/admin/scholarshipEdit.jsp").forward(request, response);
    }

    private void createScholarship(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String eligibility = request.getParameter("eligibility");

        // Parse the deadline string into a Timestamp object
        Timestamp deadline = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedDate = dateFormat.parse(request.getParameter("deadline"));
            deadline = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid deadline format. Please use yyyy-MM-ddTHH:mm");
            request.getRequestDispatcher("/WEB-INF/pages/admin/scholarshipCreate.jsp").forward(request, response);
            return;
        }

        int createdBy = user.getUserId();

        Scholarship newScholarship = new Scholarship(title, description, amount, eligibility, deadline, createdBy);
        boolean created = scholarshipService.createScholarship(newScholarship);

        if (created) {
            response.sendRedirect(request.getContextPath() + "/admin/scholarships?action=list");
        } else {
            request.setAttribute("error", "Failed to create scholarship.");
            request.getRequestDispatcher("/WEB-INF/pages/admin/scholarshipCreate.jsp").forward(request, response);
        }
    }

    private void updateScholarship(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int scholarshipId = Integer.parseInt(request.getParameter("scholarshipId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String eligibility = request.getParameter("eligibility");

        // Parse the deadline string into a Timestamp object
        Timestamp deadline = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedDate = dateFormat.parse(request.getParameter("deadline"));
            deadline = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid deadline format. Please use yyyy-MM-ddTHH:mm");
            request.getRequestDispatcher("/WEB-INF/pages/admin/scholarshipEdit.jsp").forward(request, response);
            return;
        }

        Scholarship scholarship = new Scholarship();
        scholarship.setScholarshipId(scholarshipId);
        scholarship.setTitle(title);
        scholarship.setDescription(description);
        scholarship.setAmount(amount);
        scholarship.setEligibility(eligibility);
        scholarship.setDeadline(deadline);

        boolean updated = scholarshipService.updateScholarship(scholarship);

        if (updated) {
            response.sendRedirect(request.getContextPath() + "/admin/scholarships?action=list");
        } else {
            request.setAttribute("error", "Failed to update scholarship.");
            request.getRequestDispatcher("/WEB-INF/pages/admin/scholarshipEdit.jsp").forward(request, response);
        }
    }

    private void deleteScholarship(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int scholarshipId = Integer.parseInt(request.getParameter("id"));
        boolean deleted = scholarshipService.deleteScholarship(scholarshipId);

        if (deleted) {
             response.sendRedirect(request.getContextPath() + "/admin/scholarships?action=list");
        } else {
            request.setAttribute("error", "Failed to delete scholarship.");
            listScholarships(request, response);
        }
    }
}