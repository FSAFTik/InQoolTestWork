package com.example.TennisReservation.Services.Surfaces;

import com.example.TennisReservation.Entities.Surface;

import java.util.List;
import java.util.Optional;

public interface SurfaceService {
    Surface createSurface(Surface surface);
    Optional<Surface> getSurface(long id);
    List<Surface> getAllSurfaces();
    Surface updateSurface(long id, Surface surface);
    void deleteSurface(long id);
    boolean canDeleteSurface(long id);
}