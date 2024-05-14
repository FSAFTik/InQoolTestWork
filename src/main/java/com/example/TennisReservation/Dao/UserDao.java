package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This class is responsible for handling user-related data operations.
 */
@Repository
@RequiredArgsConstructor
public class UserDao{
    private final SessionFactory sessionFactory;

    /**
     * Saves a user to the database.
     * If the user already exists, it updates the existing record.
     * @param user The user to save.
     * @return The saved user.
     */
    public User save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        return user;
    }

    /**
     * Retrieves a user by their username.
     * If the user does not exist or is not enabled, it returns an empty Optional.
     * @param username The username of the user to retrieve.
     * @return An Optional containing the user if found and enabled, or an empty Optional if not.
     */
    public Optional<User> findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, username);
        if (user == null || !user.isEnabled()){
            return Optional.empty();
        }
        return Optional.of(user);
    }

    /**
     * Disables a user by their username.
     * It retrieves the user and sets their enabled status to false.
     * @param username The username of the user to disable.
     */
    public void deleteById(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, username);

        user.setEnabled(false);
        session.update(user);
    }
}