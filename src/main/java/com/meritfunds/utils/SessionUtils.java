package com.meritfunds.utils;

import com.meritfunds.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {
    private static final String USER_SESSION_ATTR = "currentUser";

    public static void createUserSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_SESSION_ATTR, user);
        // Set session timeout (30 minutes)
        session.setMaxInactiveInterval(30 * 60);
    }

    public static User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (User) session.getAttribute(USER_SESSION_ATTR) : null;
    }

    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public static boolean isUserLoggedIn(HttpServletRequest request) {
        return getCurrentUser(request) != null;
    }
}