package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Dao.CourtDao;
import com.example.TennisReservation.Entities.Court;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class CourtDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query<Court> query;

    @InjectMocks
    private CourtDao courtDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery(any(String.class), any(Class.class))).thenReturn(query);
        when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    }

    @Test
    public void findById_ValidId_ReturnsCourt() {
        Court court = new Court();
        when(query.uniqueResultOptional()).thenReturn(Optional.of(court));

        Optional<Court> result = courtDao.findById(1L);

        assertEquals(Optional.of(court), result);
    }

    @Test
    public void findById_InvalidId_ReturnsEmpty() {
        when(query.uniqueResultOptional()).thenReturn(Optional.empty());

        Optional<Court> result = courtDao.findById(1L);

        assertEquals(Optional.empty(), result);
    }

}
