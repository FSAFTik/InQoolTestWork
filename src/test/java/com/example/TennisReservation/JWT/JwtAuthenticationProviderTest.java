package com.example.TennisReservation.JWT;

import com.example.TennisReservation.Entities.LoginRequest;
import com.example.TennisReservation.JWT.JwtAuthenticationProvider;
import com.example.TennisReservation.Services.Users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtAuthenticationProviderTest {

    @Mock
    private UserService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtAuthenticationProvider = new JwtAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Test
    public void authenticate_ValidCredentials_ReturnsAuthentication() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        UserDetails userDetails = new User("username", "password", Collections.singletonList(new SimpleGrantedAuthority("USER")));

        when(userDetailsService.loadUserByUsername(loginRequest.getUsername())).thenReturn(userDetails);
        when(passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())).thenReturn(true);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest, null);
        assertNotNull(jwtAuthenticationProvider.authenticate(authenticationToken));
    }

    @Test
    public void authenticate_InvalidCredentials_ThrowsBadCredentialsException() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        UserDetails userDetails = new User("username", "wrongPassword", Collections.singletonList(new SimpleGrantedAuthority("USER")));

        when(userDetailsService.loadUserByUsername(loginRequest.getUsername())).thenReturn(userDetails);
        when(passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())).thenReturn(false);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest, null);
        assertThrows(BadCredentialsException.class, () -> jwtAuthenticationProvider.authenticate(authenticationToken));
    }

    @Test
    public void supports_CorrectAuthenticationClass_ReturnsTrue() {
        assertTrue(jwtAuthenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void supports_IncorrectAuthenticationClass_ReturnsFalse() {
        assertFalse(jwtAuthenticationProvider.supports(Object.class));
    }
}
