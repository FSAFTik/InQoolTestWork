package com.example.TennisReservation.Services.Users;
import com.example.TennisReservation.Dao.UserDao;
import com.example.TennisReservation.Entities.Role;
import com.example.TennisReservation.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUser(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User createUser(User user) {
        user.setEnabled(true);
        return userDao.save(user);
    }

    public Optional<User> getUser(String username) {
        return userDao.findByUsername(username);
    }

    public User updateUser(String username, String password, Set<Role> roles) {
        User user = getUser(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(password);
        user.setRoles(roles);
        userDao.save(user);
        return user;
    }

    public void deleteUser(String username) {
        User user = getUser(username).orElseThrow(() -> new RuntimeException("User not found"));
        userDao.deleteById(user.getUsername());
    }
}