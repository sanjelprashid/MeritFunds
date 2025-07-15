package com.meritfunds.controller;

import com.meritfunds.models.ApplicationScholarship;
import com.meritfunds.models.User;
import com.meritfunds.services.ApplicationServices;
import com.meritfunds.utils.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/applications")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,       // 10 MB
        maxRequestSize = 1024 * 1024 * 100)    // 100 MB
public class ApplicationController extends HttpServlet {

    private final ApplicationServices applicationService = new ApplicationServices();
    private static final String UPLOAD_DIRECTORY = "uploads/applications"; // relative path
  //  private static final String DEFAULT_UPLOAD_PATH = "\\MeritFunds\\src\\main\\webapp\\uploads";
    private static final String DEFAULT_UPLOAD_PATH = "C:\\Users\\sanje\\eclipse-workspace\\MeritFunds\\src\\main\\webapp\\uploads";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("apply".equals(action)) {
            showApplyForm(request, response);
        } else if ("list".equals(action)) {
            listApplications(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("submit".equals(action)) {
            submitApplication(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    private void showApplyForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int scholarshipId = Integer.parseInt(request.getParameter("scholarshipId"));
        request.setAttribute("scholarshipId", scholarshipId);
        request.getRequestDispatcher("/WEB-INF/pages/applyScholarship.jsp").forward(request, response);
    }
   private void listApplications(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            User user = SessionUtils.getCurrentUser(request);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        List<ApplicationScholarship> applications = applicationService.getApplicationsByUserId(user.getUserId());
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("/WEB-INF/pages/listApplications.jsp").forward(request, response);
    }

    private void submitApplication(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = SessionUtils.getCurrentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int scholarshipId = Integer.parseInt(request.getParameter("scholarshipId"));
        Part filePart = request.getPart("applicationImage"); // Retrieves <input type="file" name="applicationImage">
         String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Get filename.
        String absoluteUploadPath = DEFAULT_UPLOAD_PATH + File.separator + UPLOAD_DIRECTORY;

        // Ensure upload directory exists
        File uploadDir = new File(absoluteUploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // create directories if they don't exist
        }

        // Save the file
        com.meritfunds.utils.ImageUtils.saveImage(filePart.getInputStream(), absoluteUploadPath, fileName);

        // Store relative path in DB
        String applicationImage = UPLOAD_DIRECTORY + "/" + fileName;
        ApplicationScholarship application = new ApplicationScholarship(user.getUserId(), scholarshipId,"Pending",applicationImage);
        boolean created = applicationService.createApplication(application);

        if (created) {
            response.sendRedirect(request.getContextPath() + "/applications?action=list");
        } else {
            request.setAttribute("error", "Failed to submit application.");
            request.getRequestDispatcher("/WEB-INF/pages/applyScholarship.jsp").forward(request, response);
        }
    }
}