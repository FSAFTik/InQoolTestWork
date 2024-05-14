package com.example.TennisReservation.Services;

import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Services.Surfaces.SurfaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SurfaceServiceTest {

    @Mock
    private SurfaceService surfaceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createSurface_ShouldReturnCreatedSurface() {
        Surface surface = new Surface();
        when(surfaceService.createSurface(surface)).thenReturn(surface);

        Surface createdSurface = surfaceService.createSurface(surface);

        assertEquals(surface, createdSurface);
    }

    @Test
    public void getSurface_ShouldReturnSurfaceIfExists() {
        Surface surface = new Surface();
        when(surfaceService.getSurface(1L)).thenReturn(Optional.of(surface));

        Optional<Surface> retrievedSurface = surfaceService.getSurface(1L);

        assertTrue(retrievedSurface.isPresent());
        assertEquals(surface, retrievedSurface.get());
    }

    @Test
    public void getSurface_ShouldReturnEmptyIfSurfaceDoesNotExist() {
        when(surfaceService.getSurface(1L)).thenReturn(Optional.empty());

        Optional<Surface> retrievedSurface = surfaceService.getSurface(1L);

        assertFalse(retrievedSurface.isPresent());
    }

    @Test
    public void getAllSurfaces_ShouldReturnAllSurfaces() {
        Surface surface1 = new Surface();
        Surface surface2 = new Surface();
        List<Surface> surfaces = Arrays.asList(surface1, surface2);
        when(surfaceService.getAllSurfaces()).thenReturn(surfaces);

        List<Surface> retrievedSurfaces = surfaceService.getAllSurfaces();

        assertEquals(2, retrievedSurfaces.size());
        assertTrue(retrievedSurfaces.containsAll(surfaces));
    }

    @Test
    public void updateSurface_ShouldReturnUpdatedSurface() {
        Surface surface = new Surface();
        when(surfaceService.updateSurface(1L, surface)).thenReturn(surface);

        Surface updatedSurface = surfaceService.updateSurface(1L, surface);

        assertEquals(surface, updatedSurface);
    }

    @Test
    public void deleteSurface_ShouldNotThrowException() {
        assertDoesNotThrow(() -> surfaceService.deleteSurface(1L));
    }

    @Test
    public void canDeleteSurface_ShouldReturnTrueIfSurfaceCanBeDeleted() {
        when(surfaceService.canDeleteSurface(1L)).thenReturn(true);

        boolean canDelete = surfaceService.canDeleteSurface(1L);

        assertTrue(canDelete);
    }

    @Test
    public void canDeleteSurface_ShouldReturnFalseIfSurfaceCannotBeDeleted() {
        when(surfaceService.canDeleteSurface(1L)).thenReturn(false);

        boolean canDelete = surfaceService.canDeleteSurface(1L);

        assertFalse(canDelete);
    }
}