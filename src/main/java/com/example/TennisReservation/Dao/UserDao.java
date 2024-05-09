package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao{
    private final SessionFactory sessionFactory;

    public User save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        return user;
    }

    public Optional<User> findById(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, username);
        return Optional.ofNullable(user);
    }

    public void deleteById(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, username);
        if (user != null) {
            session.delete(user);
        }
    }
}
