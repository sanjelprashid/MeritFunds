package com.meritfunds.controller;

import com.meritfunds.models.User;
import com.meritfunds.services.UserServices;
import com.meritfunds.utils.CookieUtils;
import com.meritfunds.utils.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final UserServices userService = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean rememberMe = "on".equals(request.getParameter("rememberMe"));

        User user = userService.loginUser(email, password);
        
        if (user != null) {
            // Create session
            SessionUtils.createUserSession(request, user);
            
            // Set remember me cookie if requested
            if (rememberMe) {
                CookieUtils.createRememberMeCookie(response, user.getUserId());
            }
            
            // Redirect based on role
            String redirectPath = user.getRole().equals("admin") ? "/admin/dashboard" : "/user/dashboard";
            response.sendRedirect(request.getContextPath() + redirectPath);
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check for remember me cookie
        Integer userId = CookieUtils.getRememberedUserId(request);
        if (userId != null) {
            User user = userService.getUserById(userId);
            if (user != null) {
                SessionUtils.createUserSession(request, user);
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
        }
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }
}