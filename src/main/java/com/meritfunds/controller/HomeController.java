package com.meritfunds.controller;

import com.meritfunds.models.Scholarship;
import com.meritfunds.services.ScholarshipServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ScholarshipServices scholarshipService = new ScholarshipServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("view".equals(action)) {
            showScholarshipDetails(request, response);
        } else {
            listScholarships(request, response);
        }
    }

    private void listScholarships(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        request.setAttribute("scholarships", scholarships);
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }

    private void showScholarshipDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int scholarshipId = Integer.parseInt(request.getParameter("id"));
        Scholarship scholarship = scholarshipService.getScholarshipById(scholarshipId);
        request.setAttribute("scholarship", scholarship);
        request.getRequestDispatcher("/WEB-INF/pages/scholarshipDetails.jsp").forward(request, response);
    }
}