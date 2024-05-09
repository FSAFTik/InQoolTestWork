package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Court;

import org.hibernate.SessionFactory;


import java.util.Optional;


public class CourtDao extends Dao<Court> {
    public CourtDao(SessionFactory sessionFactory) {
        super(sessionFactory, Court.class);
    }

    public Optional<Court> findById(long id) {
        return getCurrentSession()
                .createQuery("SELECT r FROM Court r WHERE r.id = :id AND r.deleted = false", Court.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }
}
