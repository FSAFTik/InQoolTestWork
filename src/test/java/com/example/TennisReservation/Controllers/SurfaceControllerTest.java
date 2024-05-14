package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Services.Surfaces.SurfaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class SurfaceControllerTest {

    @Mock
    private SurfaceService surfaceService;

    @InjectMocks
    private SurfaceController surfaceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addSurface_ValidSurface_ReturnsSurface() {
        Surface surface = new Surface();
        when(surfaceService.createSurface(any(Surface.class))).thenReturn(surface);

        ResponseEntity<Surface> response = surfaceController.addSurface(surface);

        assertEquals(ResponseEntity.ok(surface), response);
    }

    @Test
    public void getAllSurfaces_SurfacesExist_ReturnsSurfaceList() {
        List<Surface> surfaces = Arrays.asList(new Surface(), new Surface());
        when(surfaceService.getAllSurfaces()).thenReturn(surfaces);

        ResponseEntity<List<Surface>> response = surfaceController.getAllSurfaces();

        assertEquals(ResponseEntity.ok(surfaces), response);
    }

    @Test
    public void getSurface_ValidId_ReturnsSurface() {
        Surface surface = new Surface();
        when(surfaceService.getSurface(anyLong())).thenReturn(Optional.of(surface));

        ResponseEntity<Surface> response = surfaceController.getSurface(1L);

        assertEquals(ResponseEntity.ok(surface), response);
    }

    @Test
    public void getSurface_InvalidId_ReturnsNotFound() {
        when(surfaceService.getSurface(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Surface> response = surfaceController.getSurface(1L);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void updateSurface_ValidId_ReturnsUpdatedSurface() {
        Surface surface = new Surface();
        when(surfaceService.updateSurface(anyLong(), any(Surface.class))).thenReturn(surface);

        ResponseEntity<Surface> response = surfaceController.updateSurface(1L, surface);

        assertEquals(ResponseEntity.ok(surface), response);
    }

    @Test
    public void deleteSurface_CanDelete_ReturnsOk() {
        when(surfaceService.canDeleteSurface(anyLong())).thenReturn(true);

        ResponseEntity<Void> response = surfaceController.deleteSurface(1L);

        assertEquals(ResponseEntity.ok().build(), response);
    }

    @Test
    public void deleteSurface_CannotDelete_ReturnsBadRequest() {
        when(surfaceService.canDeleteSurface(anyLong())).thenReturn(false);

        ResponseEntity<Void> response = surfaceController.deleteSurface(1L);

        assertEquals(ResponseEntity.status(400).build(), response);
    }
}