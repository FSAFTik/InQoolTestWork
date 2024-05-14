package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Entities.Reservation;
import org.hibernate.SessionFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for handling reservation-related data operations.
 * It extends the Dao class, inheriting its methods and properties.
 */
public class ReservationDao extends Dao<Reservation> {

    /**
     * Constructor for the ReservationDao class.
     * @param sessionFactory The session factory to be used for database operations.
     */
    public ReservationDao(SessionFactory sessionFactory) {
        super(sessionFactory, Reservation.class);
    }

    /**
     * Retrieves all reservations for a given phone number.
     * The results are sorted by creation time in ascending order.
     * @param phoneNumber The phone number to retrieve reservations for.
     * @return A list of all reservations for the given phone number.
     */
    public List<Reservation> getAllReservations(String phoneNumber) {
        return getCurrentSession()
                .createQuery("SELECT r FROM Reservation r JOIN r.customer c WHERE c.phoneNumber = :phoneNumber AND r.deleted = false", Reservation.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList().stream().sorted(Comparator.comparing(Reservation::getCreationTime,  Comparator.nullsLast(Comparator.naturalOrder()))).toList();
    }

    /**
     * Retrieves all reservations for a given court.
     * The results are sorted by creation time in ascending order.
     * @param court The court to retrieve reservations for.
     * @return A list of all reservations for the given court.
     */
    public List<Reservation> getAllReservations(Court court) {
        return getCurrentSession()
                .createQuery("SELECT r FROM Reservation r JOIN r.court c WHERE c.id = :courtId AND r.deleted = false", Reservation.class)
                .setParameter("courtId", court.getId())
                .getResultList().stream().sorted(Comparator.comparing(Reservation::getCreationTime,  Comparator.nullsLast(Comparator.naturalOrder()))).toList();
    }

    /**
     * Retrieves a reservation by its ID.
     * @param id The ID of the reservation to retrieve.
     * @return An Optional containing the reservation if found, or an empty Optional if not.
     */
    public Optional<Reservation> findById(long id) {
        return getCurrentSession()
                .createQuery("SELECT r FROM Reservation r WHERE r.id = :id AND r.deleted = false", Reservation.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }
}