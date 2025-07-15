package com.meritfunds.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/about", "/contact"})
public class AboutContactController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/about".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/pages/about.jsp").forward(request, response);
        } else if ("/contact".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(request, response);
        } else {
            // Handle invalid path (optional)
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}