package com.example.TennisReservation.Controllers;
import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Services.Surfaces.SurfaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is responsible for handling surface-related requests.
 * It is annotated with @RestController to indicate that it's a RESTful web service controller.
 * The @RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/surfaces")
public class SurfaceController {
    private final SurfaceService surfaceService;

    /**
     * Adds a surface.
     * @param surface The surface to add.
     * @return The added surface.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Surface> addSurface(@RequestBody Surface surface) {
        return ResponseEntity.ok(surfaceService.createSurface(surface));
    }

    /**
     * Retrieves all surfaces.
     * @return A list of all surfaces.
     */
    @GetMapping
    public ResponseEntity<List<Surface>> getAllSurfaces() {
        return ResponseEntity.ok(surfaceService.getAllSurfaces());
    }

    /**
     * Retrieves a surface by ID.
     * @param id The ID of the surface.
     * @return The surface, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Surface> getSurface(@PathVariable long id) {
        return surfaceService.getSurface(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates a surface.
     * @param id The ID of the surface.
     * @param surface The updated surface.
     * @return The updated surface.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Surface> updateSurface(@PathVariable long id, @RequestBody Surface surface) {
        return ResponseEntity.ok(surfaceService.updateSurface(id, surface));
    }

    /**
     * Deletes a surface.
     * @param id The ID of the surface.
     * @return 200 OK if successful, 400 otherwise.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurface(@PathVariable long id) {
        if (surfaceService.canDeleteSurface(id)) {
            surfaceService.deleteSurface(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}