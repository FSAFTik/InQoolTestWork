package com.example.TennisReservation.Services.Users;

import com.example.TennisReservation.Entities.User;
import com.example.TennisReservation.Entities.Role;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    User createUser(String username, String password, Set<Role> roles);
    Optional<User> getUser(String username);
    User updateUser(String username, String password, Set<Role> roles);
    void deleteUser(String username);
}