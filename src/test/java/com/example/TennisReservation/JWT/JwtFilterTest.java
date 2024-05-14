package com.example.TennisReservation.JWT;

import com.example.TennisReservation.Services.Users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class JwtFilterTest {

    @Mock
    private jakarta.servlet.http.HttpServletRequest request;

    @Mock
    private jakarta.servlet.http.HttpServletResponse response;

    @Mock
    private jakarta.servlet.FilterChain filterChain;

    @Mock
    private UserService userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    private JwtFilter jwtFilter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtFilter = new JwtFilter(userDetailsService, jwtUtil);
    }

    @Test
    public void doFilterInternal_ValidJwt_SetsAuthentication() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer validJwt");
        when(jwtUtil.extractUsername("validJwt")).thenReturn("username");
        UserDetails userDetails = new User("username", "password", Collections.singletonList(new SimpleGrantedAuthority("USER")));

        when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);
        when(jwtUtil.validateToken("validJwt", userDetails)).thenReturn(true);

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void doFilterInternal_InvalidJwt_DoesNotSetAuthentication() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidJwt");
        when(jwtUtil.extractUsername("invalidJwt")).thenReturn("username");
        UserDetails userDetails = new User("username", "password", Collections.singletonList(new SimpleGrantedAuthority("USER")));

        when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);
        when(jwtUtil.validateToken("invalidJwt", userDetails)).thenReturn(false);

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void doFilterInternal_NoAuthorizationHeader_DoesNotSetAuthentication() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }
}
