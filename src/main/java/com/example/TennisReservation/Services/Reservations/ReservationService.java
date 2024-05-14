package com.example.TennisReservation.Services.Reservations;

import com.example.TennisReservation.Entities.Reservation;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract for the ReservationService.
 * It provides methods for creating, retrieving, updating, and deleting reservations.
 * It also provides methods to retrieve all reservations by phone number, court ID, and future reservations by court ID.
 */
public interface ReservationService {

    /**
     * Creates a new reservation.
     * @param reservation The reservation to be created.
     * @return The created reservation.
     */
    Reservation createReservation(Reservation reservation);

    /**
     * Retrieves all reservations by phone number.
     * @param phone_number The phone number of the user.
     * @return A list of all reservations made by the user.
     */
    List<Reservation> getAllReservations(String phone_number);

    /**
     * Retrieves all reservations by court ID.
     * @param courtId The ID of the court.
     * @return A list of all reservations for the court.
     */
    List<Reservation> getAllReservations(long courtId);

    /**
     * Retrieves all reservations.
     * @return A list of all reservations.
     */
    List<Reservation> getAllReservations();

    /**
     * Retrieves all future reservations by court ID.
     * @param courtId The ID of the court.
     * @return A list of all future reservations for the court.
     */
    List<Reservation> getAllFutureReservations(long courtId);

    /**
     * Retrieves all future reservations by phone number.
     * @param phone_number The phone number of the user.
     * @return A list of all reservations made by the user.
     */
    List<Reservation> getAllFutureReservations(String phone_number);

    /**
     * Retrieves a reservation by its ID.
     * @param id The ID of the reservation.
     * @return An Optional containing the reservation if it exists, or empty if it does not.
     */
    Optional<Reservation> getReservation(long id);

    /**
     * Updates a reservation.
     * @param id The ID of the reservation to be updated.
     * @param reservation The reservation data to update.
     * @return The updated reservation.
     */
    Reservation updateReservation(long id, Reservation reservation);

    /**
     * Deletes a reservation by its ID.
     * @param id The ID of the reservation to be deleted.
     */
    void deleteReservation(long id);
}