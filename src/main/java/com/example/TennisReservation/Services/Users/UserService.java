package com.example.TennisReservation.Services.Users;

import com.example.TennisReservation.Entities.User;
import com.example.TennisReservation.Entities.Role;

import java.util.Optional;
import java.util.Set;

/**
 * This interface extends the UserDetailsService from Spring Security and defines the contract for the UserService.
 * It provides methods for creating, retrieving, updating, and deleting users.
 */
public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService{

    /**
     * Creates a new user.
     * @param user The user to be created.
     * @return The created user.
     */
    User createUser(User user);

    /**
     * Retrieves a user by their username.
     * @param username The username of the user.
     * @return An Optional containing the user if they exist, or empty if they do not.
     */
    Optional<User> getUser(String username);

    /**
     * Updates a user's password and roles.
     * @param username The username of the user to be updated.
     * @param password The new password for the user.
     * @param roles The new set of roles for the user.
     * @return The updated user.
     */
    User updateUser(String username, String password, Set<Role> roles);

    /**
     * Deletes a user by their username.
     * @param username The username of the user to be deleted.
     */
    void deleteUser(String username);
}