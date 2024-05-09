package com.example.TennisReservation.Services.Reservations;

import com.example.TennisReservation.Entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> getAllReservations(String phone_number);
    List<Reservation> getAllReservations(long courtId);
    List<Reservation> getAllReservations();
    List<Reservation> getAllFutureReservations(long courtId);
    Optional<Reservation> getReservation(long id);
    Reservation updateReservation(long id, Reservation reservation);
    void deleteReservation(long id);
}
