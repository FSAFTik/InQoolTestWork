package com.example.TennisReservation.JWT;

import com.example.TennisReservation.Entities.LoginRequest;
import com.example.TennisReservation.Services.Users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class provides a custom authentication provider for JWT authentication.
 * It implements the AuthenticationProvider interface from Spring Security.
 * It is annotated with @Component and @RequiredArgsConstructor.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    /**
     * The user service to load user details.
     */
    private final UserService userDetailsService;

    /**
     * The password encoder to match the user's password.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Authenticates the user's credentials.
     * If the credentials are valid, it returns an authenticated UsernamePasswordAuthenticationToken.
     * If the credentials are invalid, it throws a BadCredentialsException.
     * @param authentication The authentication request object.
     * @return An authenticated UsernamePasswordAuthenticationToken.
     * @throws AuthenticationException If the credentials are invalid.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginRequest loginRequest = (LoginRequest) authentication.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("Invalid username/password");
    }

    /**
     * Indicates whether this provider supports the given authentication type.
     * It supports UsernamePasswordAuthenticationToken.
     * @param authentication The authentication type.
     * @return A boolean indicating whether this provider supports the given authentication type.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}