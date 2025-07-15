package com.meritfunds.controller;

import com.meritfunds.models.User;

import com.meritfunds.services.UserServices;
import com.meritfunds.utils.ImageUtils;
import com.meritfunds.utils.ValidationUtils;

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

@WebServlet("/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,       // 10 MB
        maxRequestSize = 1024 * 1024 * 100)   // 100 MB
public class SignupController extends HttpServlet {

    private final UserServices userService = new UserServices();
    private static final String UPLOAD_DIRECTORY = "users"; // Relative path
    private static final String DEFAULT_UPLOAD_PATH = "C:\\Users\\sanje\\eclipse-workspace\\MeritFunds\\src\\main\\webapp\\uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "student"; // Default role
        Part filePart = request.getPart("profileImage");

        // Validate inputs
        if (!ValidationUtils.isValidName(name)) {
            request.setAttribute("error", "Invalid name. Please use only letters and spaces, and at least 2 characters.");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtils.isValidEmail(email)) {
            request.setAttribute("error", "Invalid email format.");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtils.isValidPassword(password)) {
            request.setAttribute("error", "Invalid password. Must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, and one special character.");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        // Handle file upload
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String absoluteUploadPath = DEFAULT_UPLOAD_PATH + File.separator + UPLOAD_DIRECTORY;
        System.out.println("Pathhhhhhh.....................");
        System.out.println(absoluteUploadPath);

        // Ensure upload directory exists
        File uploadDir = new File(absoluteUploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // create directories if they don't exist
        }
        System.out.println(absoluteUploadPath);

        // Save the file
        ImageUtils.saveImage(filePart.getInputStream(), absoluteUploadPath, fileName);

        // Store relative path in DB
        String profileImage = UPLOAD_DIRECTORY + "/" + fileName;

        // Create new user
        User newUser = new User(name, email, password, role, profileImage);
        boolean registrationSuccess = userService.registerUser(newUser);

        if (registrationSuccess) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to login
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }
}