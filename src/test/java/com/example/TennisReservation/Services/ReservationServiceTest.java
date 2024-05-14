package com.example.TennisReservation.Services;

import com.example.TennisReservation.Entities.Reservation;
import com.example.TennisReservation.Services.Reservations.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createReservation_ShouldReturnCreatedReservation() {
        Reservation reservation = new Reservation();
        when(reservationService.createReservation(reservation)).thenReturn(reservation);

        Reservation createdReservation = reservationService.createReservation(reservation);

        assertEquals(reservation, createdReservation);
    }

    @Test
    public void getReservation_ShouldReturnReservationIfExists() {
        Reservation reservation = new Reservation();
        when(reservationService.getReservation(1L)).thenReturn(Optional.of(reservation));

        Optional<Reservation> retrievedReservation = reservationService.getReservation(1L);

        assertTrue(retrievedReservation.isPresent());
        assertEquals(reservation, retrievedReservation.get());
    }

    @Test
    public void getReservation_ShouldReturnEmptyIfReservationDoesNotExist() {
        when(reservationService.getReservation(1L)).thenReturn(Optional.empty());

        Optional<Reservation> retrievedReservation = reservationService.getReservation(1L);

        assertFalse(retrievedReservation.isPresent());
    }

    @Test
    public void getAllReservations_ShouldReturnAllReservations() {
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        List<Reservation> retrievedReservations = reservationService.getAllReservations();

        assertEquals(2, retrievedReservations.size());
        assertTrue(retrievedReservations.containsAll(reservations));
    }

    @Test
    public void updateReservation_ShouldReturnUpdatedReservation() {
        Reservation reservation = new Reservation();
        when(reservationService.updateReservation(1L, reservation)).thenReturn(reservation);

        Reservation updatedReservation = reservationService.updateReservation(1L, reservation);

        assertEquals(reservation, updatedReservation);
    }

    @Test
    public void deleteReservation_ShouldNotThrowException() {
        assertDoesNotThrow(() -> reservationService.deleteReservation(1L));
    }
}