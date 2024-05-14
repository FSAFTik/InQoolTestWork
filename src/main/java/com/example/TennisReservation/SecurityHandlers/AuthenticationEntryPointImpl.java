package com.example.TennisReservation.SecurityHandlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * This class implements the AuthenticationEntryPoint interface from Spring Security.
 * It provides a custom entry point for authentication, which is called when an unauthenticated user tries to access a secured resource.
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    /**
     * Commences the authentication process.
     * This method is called when an unauthenticated user tries to access a secured resource.
     * It sets the HTTP response status to 401 (Unauthorized) and writes a custom error message to the response.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param authException The authentication exception.
     * @throws IOException If an input or output error is detected when the servlet handles the request.
     * @throws ServletException If the request could not be handled.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized: Authentication token is missing or invalid.");
    }
}