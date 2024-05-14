package com.example.TennisReservation.SecurityHandlers;

import jakarta.servlet.ServletException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class implements the AccessDeniedHandler interface from Spring Security.
 * It provides a custom handler for access denied exceptions.
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler{

    /**
     * Handles access denied exception.
     * It sets the HTTP response status to 403 (Forbidden) and writes a custom error message to the response.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param accessDeniedException The access denied exception.
     * @throws IOException If an input or output error is detected when the servlet handles the request.
     * @throws ServletException If the request could not be handled.
     */
    @Override
    public void handle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Access Denied: You have no permissions to access this resource.");
    }
}