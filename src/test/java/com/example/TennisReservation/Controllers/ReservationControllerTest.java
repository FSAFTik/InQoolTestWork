package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Entities.Reservation;
import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Services.Reservations.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createReservation_ValidReservationQuatro_ReturnsPrice() {
        Surface surface = new Surface();
        surface.setType("Clay");
        surface.setPricePerHour(5000);

        Court court = new Court();
        court.setSurface(surface);

        Reservation reservation = new Reservation();
        reservation.setCourt(court);
        reservation.setStartTime(LocalDateTime.now().plusHours(1));
        reservation.setEndTime(LocalDateTime.now().plusHours(2));
        reservation.setDuo(false);
        when(reservationService.createReservation(any(Reservation.class))).thenReturn(reservation);

        ResponseEntity<String> response = reservationController.createReservation(reservation);

        assertEquals(ResponseEntity.ok(reservation.getPrice()), response);
        assertEquals("75.0", reservation.getPrice());
    }

    @Test
    public void createReservation_ValidReservationDuo_ReturnsPrice() {
        Surface surface = new Surface();
        surface.setType("Clay");
        surface.setPricePerHour(5000);

        Court court = new Court();
        court.setSurface(surface);

        Reservation reservation = new Reservation();
        reservation.setCourt(court);
        reservation.setStartTime(LocalDateTime.now().plusHours(1));
        reservation.setEndTime(LocalDateTime.now().plusHours(2));
        reservation.setDuo(true);
        when(reservationService.createReservation(any(Reservation.class))).thenReturn(reservation);

        ResponseEntity<String> response = reservationController.createReservation(reservation);

        assertEquals(ResponseEntity.ok(reservation.getPrice()), response);
        assertEquals("50.0", reservation.getPrice());
    }

    @Test
    public void getAllReservations_ReservationsExist_ReturnsReservationList() {
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(reservationService.getAllReservations()).thenReturn(reservations);

        ResponseEntity<List<Reservation>> response = reservationController.getAllReservations();

        assertEquals(ResponseEntity.ok(reservations), response);
    }

    @Test
    public void getReservationById_ValidId_ReturnsReservation() {
        Reservation reservation = new Reservation();
        when(reservationService.getReservation(anyLong())).thenReturn(Optional.of(reservation));

        ResponseEntity<Reservation> response = reservationController.getReservationById(1L);
        assertEquals(ResponseEntity.ok(reservation), response);
    }

    @Test
    public void getReservationById_InvalidId_ReturnsNotFound() {
        when(reservationService.getReservation(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Reservation> response = reservationController.getReservationById(1L);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void updateReservation_ValidReservationSingle_ReturnsUpdatedReservation() {
        Surface surface = new Surface();
        surface.setType("Clay");
        surface.setPricePerHour(50);

        Court court = new Court();
        court.setSurface(surface);

        Reservation reservation = new Reservation();
        reservation.setCourt(court);
        reservation.setStartTime(LocalDateTime.now().plusHours(1)); // set start time
        reservation.setEndTime(LocalDateTime.now().plusHours(2)); // set end time
        reservation.setDuo(false); // set isDuo to false
        when(reservationService.updateReservation(anyLong(), any(Reservation.class))).thenReturn(reservation);

        ResponseEntity<String> response = reservationController.updateReservation(1L, reservation);

        assertEquals(ResponseEntity.ok(reservation.getPrice()), response);
    }

    @Test
    public void updateReservation_ValidReservationDuo_ReturnsUpdatedReservation() {
        Surface surface = new Surface();
        surface.setType("Clay");
        surface.setPricePerHour(50);

        Court court = new Court();
        court.setSurface(surface);

        Reservation reservation = new Reservation();
        reservation.setCourt(court);
        reservation.setStartTime(LocalDateTime.now().plusHours(1)); // set start time
        reservation.setEndTime(LocalDateTime.now().plusHours(2)); // set end time
        reservation.setDuo(true); // set isDuo to true
        when(reservationService.updateReservation(anyLong(), any(Reservation.class))).thenReturn(reservation);

        ResponseEntity<String> response = reservationController.updateReservation(1L, reservation);

        assertEquals(ResponseEntity.ok(reservation.getPrice()), response);
    }

    @Test
    public void deleteReservation_CanDelete_ReturnsOk() {
        ResponseEntity<Void> response = reservationController.deleteReservation(1L);

        assertEquals(ResponseEntity.ok().build(), response);
    }
}