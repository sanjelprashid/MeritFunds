package com.meritfunds.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {
    private static final String REMEMBER_ME_COOKIE = "rememberMe";
    private static final int COOKIE_MAX_AGE = 30 * 24 * 60 * 60; // 30 days

    public static void createRememberMeCookie(HttpServletResponse response, int userId) {
        Cookie cookie = new Cookie(REMEMBER_ME_COOKIE, String.valueOf(userId));
        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        // In production, add: cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public static Integer getRememberedUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (REMEMBER_ME_COOKIE.equals(cookie.getName())) {
                    try {
                        return Integer.parseInt(cookie.getValue());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public static void deleteRememberMeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(REMEMBER_ME_COOKIE, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}