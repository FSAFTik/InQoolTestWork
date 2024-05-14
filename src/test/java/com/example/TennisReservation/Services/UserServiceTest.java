package com.example.TennisReservation.Services;

import com.example.TennisReservation.Entities.Role;
import com.example.TennisReservation.Entities.User;
import com.example.TennisReservation.Services.Users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ShouldReturnCreatedUser() {
        User user = new User();
        when(userService.createUser(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser((User.builder().username("user").password("user").roles(Collections.singleton(Role.USER)).build()));

        assertEquals(user, createdUser);
    }

    @Test
    public void getUser_ShouldReturnUserIfExists() {
        User user = new User();
        when(userService.getUser("username")).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUser("username");

        assertTrue(retrievedUser.isPresent());
        assertEquals(user, retrievedUser.get());
    }

    @Test
    public void getUser_ShouldReturnEmptyIfUserDoesNotExist() {
        when(userService.getUser("username")).thenReturn(Optional.empty());

        Optional<User> retrievedUser = userService.getUser("username");

        assertFalse(retrievedUser.isPresent());
    }

    @Test
    public void updateUser_ShouldReturnUpdatedUser() {
        Set<Role> roles = new HashSet<>();
        User user = new User();
        when(userService.updateUser("username", "password", roles)).thenReturn(user);

        User updatedUser = userService.updateUser("username", "password", roles);

        assertEquals(user, updatedUser);
    }

    @Test
    public void deleteUser_ShouldNotThrowException() {
        assertDoesNotThrow(() -> userService.deleteUser("username"));
    }
}
