package com.example.TennisReservation.JWT;

import com.example.TennisReservation.Entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtUtilTest {

    @Mock
    private User userDetails;

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "SECRET_KEY", "mySecretKey");
        ReflectionTestUtils.setField(jwtUtil, "JWT_EXPIRATION", 1000L);
    }

    @Test
    public void generateToken_ValidUserDetails_ReturnsToken() {
        when(userDetails.getUsername()).thenReturn("username");
        String token = jwtUtil.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    public void validateToken_ValidTokenAndUserDetails_ReturnsTrue() {
        when(userDetails.getUsername()).thenReturn("username");
        String token = jwtUtil.generateToken(userDetails);
        assertTrue(jwtUtil.validateToken(token, userDetails));
    }

    @Test
    public void validateToken_ExpiredToken_ReturnsFalse() {
        when(userDetails.getUsername()).thenReturn("username");
        String token = generateExpiredToken(userDetails);
        try {
            assertFalse(jwtUtil.validateToken(token, userDetails));
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            assertTrue(true);
        }
    }

    public String generateExpiredToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() - 1000))
                .signWith(SignatureAlgorithm.HS256, "mySecretKey").compact();
    }

    @Test
    public void validateToken_WrongUsername_ReturnsFalse() {
        when(userDetails.getUsername()).thenReturn("username");
        String token = jwtUtil.generateToken(userDetails);
        when(userDetails.getUsername()).thenReturn("wrongUsername");
        assertFalse(jwtUtil.validateToken(token, userDetails));
    }
}