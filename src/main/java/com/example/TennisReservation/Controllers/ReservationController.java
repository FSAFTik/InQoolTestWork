package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.Reservation;
import com.example.TennisReservation.Services.Reservations.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        try{
            Reservation newReservation = reservationService.createReservation(reservation);
            System.out.println(newReservation.getPrice());
            return ResponseEntity.ok(newReservation.getPrice());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long id) {
        return reservationService.getReservation(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/court/{court_id}")
    public ResponseEntity<List<Reservation>> getReservationByCourtId(@PathVariable long court_id) {
        return ResponseEntity.ok(reservationService.getAllReservations(court_id));
    }

    @GetMapping("/court/{court_id}/future")
    public ResponseEntity<List<Reservation>> getFutureReservationByCourtId(@PathVariable long court_id) {
        return ResponseEntity.ok(reservationService.getAllFutureReservations(court_id));
    }

    @GetMapping("/phone/{phone_number}")
    public ResponseEntity<List<Reservation>> getReservationByPhoneNumber(@PathVariable String phone_number) {
        return ResponseEntity.ok(reservationService.getAllReservations(phone_number));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable long id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation.getPrice());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

}
