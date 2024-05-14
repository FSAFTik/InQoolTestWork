package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.Role;
import com.example.TennisReservation.Entities.User;
import com.example.TennisReservation.Services.Users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ValidUser_ReturnsUser() {
        User user = User.builder().build();
        when(userService.createUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.createUser((User.builder().username("user").password("user").roles(Collections.singleton(Role.USER)).build()));

        assertEquals(ResponseEntity.ok(user), response);
    }

    @Test
    public void getUser_ValidUsername_ReturnsUser() {
        User user = new User();
        when(userService.getUser(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUser("username");

        assertEquals(ResponseEntity.ok(user), response);
    }

    @Test
    public void updateUser_ValidUser_ReturnsUpdatedUser() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        when(userService.updateUser(anyString(), anyString(), any(Set.class))).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser("username", "password", roles);

        assertEquals(ResponseEntity.ok(user), response);
    }

    @Test
    public void deleteUser_ValidUsername_ReturnsOk() {
        ResponseEntity<Void> response = userController.deleteUser("username");

        assertEquals(ResponseEntity.ok().build(), response);
    }
}
