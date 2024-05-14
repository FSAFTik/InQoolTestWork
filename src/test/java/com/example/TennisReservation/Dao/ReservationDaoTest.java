package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Entities.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ReservationDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query<Reservation> reservationQuery;

    private ReservationDao reservationDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery(anyString(), eq(Reservation.class))).thenReturn(reservationQuery);
        when(reservationQuery.setParameter(anyString(), any())).thenReturn(reservationQuery);
        reservationDao = new ReservationDao(sessionFactory);
    }


    @Test
    public void findById_NonExistingId_ReturnsEmpty() {
        when(reservationQuery.uniqueResultOptional()).thenReturn(Optional.empty());
        Optional<Reservation> result = reservationDao.findById(1L);
        assertEquals(false, result.isPresent());
    }

    @Test
    public void getAllReservationsByPhoneNumber_ReturnsReservations() {
        String phoneNumber = "1234567890";
        List<Reservation> expectedReservations = List.of(new Reservation(), new Reservation());
        when(reservationQuery.getResultList()).thenReturn(expectedReservations);

        List<Reservation> actualReservations = reservationDao.getAllReservations(phoneNumber);

        assertEquals(expectedReservations, actualReservations);
    }


    @Test
    public void getAllReservationsByCourt_ReturnsReservations() {
        Court court = new Court();
        court.setId(1L);
        List<Reservation> expectedReservations = List.of(new Reservation(), new Reservation());
        when(reservationQuery.getResultList()).thenReturn(expectedReservations);

        List<Reservation> actualReservations = reservationDao.getAllReservations(court);

        assertEquals(expectedReservations, actualReservations);
    }


    @Test
    public void findById_ExistingId_ReturnsReservation() {
        when(reservationQuery.uniqueResultOptional()).thenReturn(Optional.of(new Reservation()));
        Optional<Reservation> result = reservationDao.findById(1L);
        assertEquals(true, result.isPresent());
    }
}