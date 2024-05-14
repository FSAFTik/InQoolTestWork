package com.example.TennisReservation.Services.Courts;

import com.example.TennisReservation.Entities.Court;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract for the CourtService.
 * It provides methods for creating, retrieving, updating, and deleting courts.
 * It also provides a method to check if a court can be deleted.
 */
public interface CourtService {

    /**
     * It is used to create a new court.
     * @param court The court to be created.
     */
    static void createCourt(Court court) {
    }

    /**
     * Creates a new court.
     * @param court The court to be created.
     * @return The created court.
     */
    Court CreateCourt(Court court);

    /**
     * Retrieves a court by its ID.
     * @param id The ID of the court.
     * @return An Optional containing the court if it exists, or empty if it does not.
     */
    Optional<Court> getCourt(long id);

    /**
     * Retrieves all courts.
     * @return A list of all courts.
     */
    List<Court> getAllCourts();

    /**
     * Updates a court.
     * @param id The ID of the court to be updated.
     * @param court The court data to update.
     * @return The updated court.
     */
    Court UpdateCourt(long id, Court court);

    /**
     * Deletes a court by its ID.
     * @param id The ID of the court to be deleted.
     */
    void deleteCourt(long id);

    /**
     * Checks if a court can be deleted.
     * @param id The ID of the court to check.
     * @return true if the court can be deleted, false otherwise.
     */
    boolean canDeleteCourt(long id);
}