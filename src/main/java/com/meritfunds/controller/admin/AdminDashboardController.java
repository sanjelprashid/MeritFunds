package com.meritfunds.controller.admin;

import com.meritfunds.models.User;
import com.meritfunds.models.ApplicationScholarship;
import com.meritfunds.services.UserServices;
import com.meritfunds.services.ApplicationServices;
import com.meritfunds.utils.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardController extends HttpServlet {

    private final UserServices userService = new UserServices();
    private final ApplicationServices applicationService = new ApplicationServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionUtils.getCurrentUser(request);
        if (user == null || !user.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get recent users
        List<User> recentUsers = userService.getRecentUsers(5);

        // Get recent applications
        List<ApplicationScholarship> recentApplications = applicationService.getRecentApplications(5);

        request.setAttribute("recentUsers", recentUsers);
        request.setAttribute("recentApplications", recentApplications);
        request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
    }
}