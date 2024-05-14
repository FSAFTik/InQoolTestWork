package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Court;
import org.hibernate.SessionFactory;
import java.util.Optional;

/**
 * This class is responsible for handling court-related data operations.
 * It extends the Dao class, inheriting its methods and properties.
 */
public class CourtDao extends Dao<Court> {

    /**
     * Constructor for the CourtDao class.
     * @param sessionFactory The session factory to be used for database operations.
     */
    public CourtDao(SessionFactory sessionFactory) {
        super(sessionFactory, Court.class);
    }

    /**
     * Retrieves a court by its ID.
     * @param id The ID of the court to retrieve.
     * @return An Optional containing the court if found, or an empty Optional if not.
     */
    public Optional<Court> findById(long id) {
        return getCurrentSession()
                .createQuery("SELECT r FROM Court r WHERE r.id = :id AND r.deleted = false", Court.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }
}