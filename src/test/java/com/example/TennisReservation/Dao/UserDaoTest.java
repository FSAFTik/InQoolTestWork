package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserDaoTest {

    @Mock
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsername_ExistingUsername_ReturnsUserDetails() {
        User user = new User();
        user.setUsername("testUser");
        when(userDao.findByUsername("testUser")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userDao.findByUsername("testUser");

        assertEquals(foundUser.get().getUsername(), user.getUsername());
    }

    @Test
    public void loadUserByUsername_NonExistingUsername_ReturnsNull() {
        when(userDao.findByUsername("nonExistingUser")).thenReturn(null);

        Optional<User> foundUser = userDao.findByUsername("nonExistingUser");

        assertNull(foundUser);
    }

    @Test
    public void save_NewUser_SavesSuccessfully() {
        User user = new User();
        user.setUsername("newUser");
        when(userDao.save(user)).thenReturn(user);

        User savedUser = userDao.save(user);

        assertEquals(savedUser.getUsername(), user.getUsername());
    }

    @Test
    public void deleteById_ExistingUser_DeletesSuccessfully() {
        User user = new User();
        user.setUsername("existingUser");
        userDao.deleteById("existingUser");

        Optional<User> deletedUser = userDao.findByUsername("existingUser");

        assertTrue(deletedUser.isEmpty());
    }
}