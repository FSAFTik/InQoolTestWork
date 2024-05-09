package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Surface;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class SurfaceDao extends Dao<Surface> {
    public SurfaceDao(SessionFactory sessionFactory) {
        super(sessionFactory, Surface.class);
    }

    public Optional<Surface> findById(long id) {
        return getCurrentSession()
                .createQuery("SELECT s FROM Surface s WHERE s.id = :id", Surface.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }
}