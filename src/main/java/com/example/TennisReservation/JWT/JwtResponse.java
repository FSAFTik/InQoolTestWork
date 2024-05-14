package com.example.TennisReservation.JWT;

/**
 * This record to represent a JWT response.
 */
public record JwtResponse(
        /**
         * The JWT token string.
         */
        String jwt
) {
}