package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Surface;
import org.hibernate.SessionFactory;

import java.util.Optional;

/**
 * This class is responsible for handling surface-related data operations.
 * It extends the Dao class, inheriting its methods and properties.
 */
public class SurfaceDao extends Dao<Surface> {

    /**
     * Constructor for the SurfaceDao class.
     * @param sessionFactory The session factory to be used for database operations.
     */
    public SurfaceDao(SessionFactory sessionFactory) {
        super(sessionFactory, Surface.class);
    }

    /**
     * Retrieves a surface by its ID.
     * @param id The ID of the surface to retrieve.
     * @return An Optional containing the surface if found, or an empty Optional if not.
     */
    public Optional<Surface> findById(long id) {
        return getCurrentSession()
                .createQuery("SELECT s FROM Surface s WHERE s.id = :id", Surface.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }
}