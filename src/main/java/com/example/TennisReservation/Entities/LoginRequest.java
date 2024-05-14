package com.example.TennisReservation.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents a request for user login.
 * It is annotated with @Data and @AllArgsConstructor.
 */
@Data
@AllArgsConstructor
public class LoginRequest {
    /**
     * The username of the user attempting to log in.
     */
    private String username;

    /**
     * The password of the user attempting to log in.
     */
    private String password;
}