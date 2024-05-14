package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Surface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SurfaceDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query<Surface> query;

    private SurfaceDao surfaceDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery(any(String.class), any(Class.class))).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        surfaceDao = new SurfaceDao(sessionFactory);
    }

    @Test
    public void findById_ExistingId_ReturnsSurface() {
        Surface expectedSurface = new Surface();
        when(query.uniqueResultOptional()).thenReturn(Optional.of(expectedSurface));

        Optional<Surface> result = surfaceDao.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(expectedSurface, result.get());
    }

    @Test
    public void findById_NonExistingId_ReturnsEmpty() {
        when(query.uniqueResultOptional()).thenReturn(Optional.empty());

        Optional<Surface> result = surfaceDao.findById(1L);

        assertTrue(result.isEmpty());
    }
}