package com.meritfunds.filter;

import com.meritfunds.models.User;
import com.meritfunds.utils.SessionUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        User user = SessionUtils.getCurrentUser(httpRequest);

        if (user != null && "admin".equals(user.getRole())) {
            // User is an admin, allow access to the requested resource
            chain.doFilter(request, response);
        } else {
            // User is not an admin, redirect to login page or display an error
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login"); // Or display an access denied page
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}