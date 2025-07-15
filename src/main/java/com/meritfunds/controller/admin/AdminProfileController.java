package com.meritfunds.controller.admin;

import com.meritfunds.models.User;
import com.meritfunds.services.UserServices;
import com.meritfunds.utils.ImageUtils;
import com.meritfunds.utils.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/admin/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,       // 10 MB
        maxRequestSize = 1024 * 1024 * 100)   // 100 MB
public class AdminProfileController extends HttpServlet {

    private final UserServices userService = new UserServices();
    private static final String UPLOAD_DIRECTORY = "users"; // Relative path
    private static final String DEFAULT_UPLOAD_PATH = "G:\\EclipsServlets\\MeritFunds\\src\\main\\webapp\\uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionUtils.getCurrentUser(request);
        if (user == null || !user.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/admin/adminProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionUtils.getCurrentUser(request);
        if (user == null || !user.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Part filePart = request.getPart("profileImage");

        String profileImage = user.getProfileImage();  // Keep existing image path if no new image is uploaded

        if (filePart != null && filePart.getSize() > 0) {
            try {
                // Handle file upload
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String absoluteUploadPath = DEFAULT_UPLOAD_PATH + File.separator + UPLOAD_DIRECTORY;

                // Ensure upload directory exists
                File uploadDir = new File(absoluteUploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs(); // create directories if they don't exist
                }

                // Save the file
                ImageUtils.saveImage(filePart.getInputStream(), absoluteUploadPath, fileName);

                // Store relative path in DB
                profileImage = UPLOAD_DIRECTORY + "/" + fileName;
            }
            catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "File upload failed: " + e.getMessage());
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/pages/admin/adminProfile.jsp").forward(request, response);
                return;
            }

        }


        // Update the user object
        user.setName(name);
        user.setEmail(email);
        user.setProfileImage(profileImage);

        // Call userService to update the user in the database
        boolean updated = userService.updateUser(user);

        if (updated) {
            // Update the session with the updated user object
            User updatedUser = userService.getUserById(user.getUserId());
            SessionUtils.createUserSession(request, updatedUser);
            response.sendRedirect(request.getContextPath() + "/admin/profile"); // Redirect back to admin profile page
        } else {
            request.setAttribute("error", "Failed to update profile.");
            request.setAttribute("user", user); // Send back current user info
            request.getRequestDispatcher("/WEB-INF/pages/admin/adminProfile.jsp").forward(request, response);
        }
    }
}