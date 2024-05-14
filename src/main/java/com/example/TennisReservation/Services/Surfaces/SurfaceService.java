package com.example.TennisReservation.Services.Surfaces;

import com.example.TennisReservation.Entities.Surface;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract for the SurfaceService.
 * It provides methods for creating, retrieving, updating, and deleting surfaces.
 * It also provides a method to check if a surface can be deleted.
 */
public interface SurfaceService {

    /**
     * Creates a new surface.
     * @param surface The surface to be created.
     * @return The created surface.
     */
    Surface createSurface(Surface surface);

    /**
     * Retrieves a surface by its ID.
     * @param id The ID of the surface.
     * @return An Optional containing the surface if it exists, or empty if it does not.
     */
    Optional<Surface> getSurface(long id);

    /**
     * Retrieves all surfaces.
     * @return A list of all surfaces.
     */
    List<Surface> getAllSurfaces();

    /**
     * Updates a surface.
     * @param id The ID of the surface to be updated.
     * @param surface The surface data to update.
     * @return The updated surface.
     */
    Surface updateSurface(long id, Surface surface);

    /**
     * Deletes a surface by its ID.
     * @param id The ID of the surface to be deleted.
     */
    void deleteSurface(long id);

    /**
     * Checks if a surface can be deleted.
     * @param id The ID of the surface to check.
     * @return true if the surface can be deleted, false otherwise.
     */
    boolean canDeleteSurface(long id);
}