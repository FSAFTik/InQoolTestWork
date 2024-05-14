package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.LoginRequest;
import com.example.TennisReservation.JWT.JwtResponse;
import com.example.TennisReservation.JWT.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is responsible for handling authentication requests.
 * It is annotated with @RestController to indicate that it's a RESTful web service controller.
 * The @RequiredArgsConstructor annotation is used to automatically generate a constructor with required arguments.
 * The @RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    // The AuthenticationManager to authenticate a request.
    private final AuthenticationManager authenticationManager;

    // The JwtUtil to generate a token.
    private final JwtUtil jwtUtil;

    /**
     * This method is responsible for authenticating a user.
     * It is annotated with @PostMapping to map HTTP POST requests onto this method.
     * The @RequestBody annotation is used to bind the request body with a method parameter.
     *
     * @param userToLogin The user to login.
     * @return ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest userToLogin) {
        // Authenticate the user.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userToLogin.getUsername(),
                        userToLogin.getPassword()
                )
        );

        // Set the authentication in the security context.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get the user details.
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Return the response entity with the generated token.
        return ResponseEntity.ok(new JwtResponse(jwtUtil.generateToken(userDetails)));
    }
}