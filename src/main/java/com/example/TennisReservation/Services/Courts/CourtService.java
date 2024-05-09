package com.example.TennisReservation.Services.Courts;

import com.example.TennisReservation.Entities.Court;

import java.util.List;
import java.util.Optional;

public interface CourtService {
    static void createCourt(Court court) {
    }

    Court CreateCourt(Court court);
    Optional<Court> getCourt(long id);
    List<Court> getAllCourts();
    Court UpdateCourt(long id, Court court);
    void deleteCourt(long id);
    boolean canDeleteCourt(long id);
}
