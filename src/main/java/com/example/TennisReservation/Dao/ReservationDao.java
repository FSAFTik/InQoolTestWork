package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Entities.Reservation;
import org.hibernate.SessionFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class ReservationDao extends Dao<Reservation> {
    public ReservationDao(SessionFactory sessionFactory) {
        super(sessionFactory, Reservation.class);
    }

    public List<Reservation> getAllReservations(String phoneNumber) {
        return getCurrentSession()
                .createQuery("SELECT r FROM Reservation r JOIN r.customer c WHERE c.phoneNumber = :phoneNumber AND r.deleted = false", Reservation.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList().stream().sorted(Comparator.comparing(Reservation::getCreationTime)).toList();
    }
    public List<Reservation> getAllReservations(Court court) {
        return getCurrentSession()
                .createQuery("SELECT r FROM Reservation r JOIN r.court c WHERE c.id = :courtId AND r.deleted = false", Reservation.class)
                .setParameter("courtId", court.getId())
                .getResultList().stream().sorted(Comparator.comparing(Reservation::getCreationTime)).toList();
    }

    public Optional<Reservation> findById(long id) {
        return getCurrentSession()
                .createQuery("SELECT r FROM Reservation r WHERE r.id = :id AND r.deleted = false", Reservation.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }
}
