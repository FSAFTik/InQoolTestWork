package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.Reservation;
import com.example.TennisReservation.Services.Reservations.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is responsible for handling reservation-related requests.
 * It is annotated with @RestController to indicate that it's a RESTful web service controller.
 * The @RequiredArgsConstructor annotation is used to automatically generate a constructor with required arguments.
 * The @RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    /**
     * Creates a reservation.
     * @param reservation The reservation to create.
     * @return The price of the new reservation, or 400 if an error occurred.
     */
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        try{
            Reservation newReservation = reservationService.createReservation(reservation);
            return ResponseEntity.ok(newReservation.getPrice());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves all reservations.
     * @return A list of all reservations.
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    /**
     * Retrieves a reservation by ID.
     * @param id The ID of the reservation.
     * @return The reservation, or 404 if not found.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long id) {
        return reservationService.getReservation(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all reservations for a specific court.
     * @param court_id The ID of the court.
     * @return A list of reservations for the court.
     */
    @GetMapping("/court/{court_id}")
    public ResponseEntity<List<Reservation>> getReservationByCourtId(@PathVariable long court_id) {
        return ResponseEntity.ok(reservationService.getAllReservations(court_id));
    }

    /**
     * Retrieves all future reservations for a specific court.
     * @param court_id The ID of the court.
     * @return A list of future reservations for the court.
     */
    @GetMapping("/court/{court_id}/future")
    public ResponseEntity<List<Reservation>> getFutureReservationByCourtId(@PathVariable long court_id) {
        return ResponseEntity.ok(reservationService.getAllFutureReservations(court_id));
    }

    /**
     * Retrieves all reservations for a specific phone number.
     * @param phone_number The phone number.
     * @return A list of reservations for the phone number.
     */
    @GetMapping("/phone/{phone_number}")
    public ResponseEntity<List<Reservation>> getReservationByPhoneNumber(@PathVariable String phone_number) {
        return ResponseEntity.ok(reservationService.getAllReservations(phone_number));
    }

    /**
     * Retrieves all future reservations for a specific phone number.
     * @param phone_number The phone number.
     * @return A list of future reservations for the phone number.
     */
    @GetMapping("/phone/{phone_number}/future")
    public ResponseEntity<List<Reservation>> getFutureReservationByCourtId(@PathVariable String phone_number) {
        return ResponseEntity.ok(reservationService.getAllFutureReservations(phone_number));
    }

    /**
     * Updates a reservation.
     * @param id The ID of the reservation.
     * @param reservation The updated reservation.
     * @return The price of the updated reservation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable long id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation.getPrice());
    }

    /**
     * Deletes a reservation.
     * @param id The ID of the reservation.
     * @return 200 OK if successful.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}