package com.meritfunds.controller.admin;

import com.meritfunds.models.ApplicationScholarship;
import com.meritfunds.models.User;
import com.meritfunds.services.ApplicationServices;
import com.meritfunds.utils.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/applications")
public class AdminApplicationController extends HttpServlet {

    private final ApplicationServices applicationService = new ApplicationServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionUtils.getCurrentUser(request);
        if (user == null || !user.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            showEditForm(request, response);
        } else if ("delete".equals(action)) {
            deleteApplication(request, response);
        } else if("listAll".equals(action)){
             listAllApplications(request,response);
        }
        else {
            listApplications(request, response);
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
        if ("update".equals(action)) {
            updateApplication(request, response);
        } else {
            listApplications(request, response);
        }
    }

    private void listApplications(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ApplicationScholarship> applications = applicationService.getAllApplications();
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("/WEB-INF/pages/admin/applicationList.jsp").forward(request, response);
    }
     private void listAllApplications(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ApplicationScholarship> applications = applicationService.getAllApplications();
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("/WEB-INF/pages/admin/listAllApplications.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int applicationId = Integer.parseInt(request.getParameter("id"));
        ApplicationScholarship application = applicationService.getApplicationById(applicationId);
        request.setAttribute("application", application);
        request.getRequestDispatcher("/WEB-INF/pages/admin/applicationEdit.jsp").forward(request, response);
    }

    private void updateApplication(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        String status = request.getParameter("status");

        boolean updated = applicationService.updateApplicationStatus(applicationId, status);

        if (updated) {
            response.sendRedirect(request.getContextPath() + "/admin/applications");
        } else {
            request.setAttribute("error", "Failed to update application status.");
            showEditForm(request, response); // Redisplay the edit form with error
        }
    }

    private void deleteApplication(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //add function to delete
        response.sendRedirect(request.getContextPath() + "/admin/applications");
    }
}