package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.User;
import com.example.TennisReservation.Entities.Role;
import com.example.TennisReservation.Services.Users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * This class is responsible for handling user-related requests.
 * It is annotated with @RestController to indicate that it's a RESTful web service controller.
 * The @RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
 * The @PreAuthorize annotation is used to ensure that only users with 'ROLE_ADMIN' can access the methods in this class.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
    private final UserService userService;

    /**
     * Creates a user.
     * @param user The user to create.
     * @return The created user.
     */
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    /**
     * Retrieves a user by username.
     * @param username The username of the user.
     * @return The user, or throws a RuntimeException if not found.
     */
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.getUser(username).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    /**
     * Updates a user.
     * @param username The username of the user.
     * @param password The new password of the user.
     * @param roles The new roles of the user.
     * @return The updated user.
     */
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody String password, @RequestBody Set<Role> roles) {
        User user = userService.updateUser(username, password, roles);
        return ResponseEntity.ok(user);
    }

    /**
     * Deletes a user.
     * @param username The username of the user.
     * @return 200 OK if successful.
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }
}