package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Services.Courts.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is responsible for handling court-related requests.
 * It is annotated with @RestController to indicate that it's a RESTful web service controller.
 * The @RequiredArgsConstructor annotation is used to automatically generate a constructor with required arguments.
 * The @RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courts")
public class CourtController {
    private final CourtService courtService;

    /**
     * Adds a court.
     * @param court The court to add.
     * @return The added court.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Court> addCourt(@RequestBody Court court) {
        return ResponseEntity.ok(courtService.CreateCourt(court));
    }

    /**
     * Retrieves all courts.
     * @return A list of all courts.
     */
    @GetMapping
    public ResponseEntity<List<Court>> getAllCourts() {
        return ResponseEntity.ok(courtService.getAllCourts());
    }

    /**
     * Retrieves a court by ID.
     * @param id The ID of the court.
     * @return The court, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Court> getCourt(@PathVariable long id) {
        return courtService.getCourt(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates a court.
     * @param id The ID of the court.
     * @param court The updated court.
     * @return The updated court.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Court> updateCourt(@PathVariable long id, @RequestBody Court court) {
        return ResponseEntity.ok(courtService.UpdateCourt(id, court));
    }

    /**
     * Deletes a court.
     * @param id The ID of the court.
     * @return 200 OK if successful, 400 otherwise.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourt(@PathVariable long id) {
        if (courtService.canDeleteCourt(id)) {
            courtService.deleteCourt(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}