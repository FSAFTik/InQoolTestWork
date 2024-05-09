package com.example.TennisReservation.Services.Users;

import com.example.TennisReservation.Dao.UserDao;
import com.example.TennisReservation.Entities.User;
import com.example.TennisReservation.Entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public User createUser(String username, String password, Set<Role> roles) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roles);
        return userDao.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        return userDao.findById(username);
    }

    @Override
    public User updateUser(String username, String password, Set<Role> roles) {
        User user = getUser(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(password);
        user.setRoles(roles);
        userDao.save(user);
        return user;
    }

    @Override
    public void deleteUser(String username) {
        User user = getUser(username).orElseThrow(() -> new RuntimeException("User not found"));
        userDao.deleteById(user.getUsername());
    }
}